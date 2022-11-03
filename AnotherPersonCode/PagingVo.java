package paging;

import java.util.List;

import org.json.JSONObject;

import dao.ProductDao;
import dto1.ProductList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingVo {
	// 클라이언트에엇 3번을 요청하면 서번에 있는 pager 객체의 3번 필을 json 으로 전달한다.
	private int totalRows;		//전체 행수
	private int totalPageNo;	//전체 페이지 수
	private int totalGroupNo;	//전체 그룹 수
	private int startPageNo;	//그룹의 시작 페이지 번호
	private int endPageNo;		//그룹의 끝 페이지 번호
	private int pageNo;			//현재 페이지 번호
	private final int pagesPerGroup = 4;	//그룹당 페이지 수
	private int groupNo;		//현재 그룹 번호
	private final int rowsPerPage = 5;	//페이지당 행 수 
	private int startRowNo;		//페이지의 시작 행 번호(1, ..., n)
	private int endRowNo;		//페이지의 마지막 행 번호
//	private boolean hasPreviousPage;
//	private boolean hasNextPage;
	
	public PagingVo(int totalRows, int pageNo) {
		this.totalRows = totalRows;

		totalPageNo = totalRows / rowsPerPage;
		if(totalRows % rowsPerPage != 0) totalPageNo++;
		
		if(pageNo > totalPageNo) this.pageNo = totalPageNo;
		else this.pageNo = pageNo;
		
		totalGroupNo = totalPageNo / pagesPerGroup;
		if(totalPageNo % pagesPerGroup != 0) totalGroupNo++;
		
		groupNo = (pageNo - 1) / pagesPerGroup + 1;
		
		startPageNo = (groupNo-1) * pagesPerGroup + 1;
		
		endPageNo = startPageNo + pagesPerGroup - 1;
		if(groupNo == totalGroupNo) endPageNo = totalPageNo;
		
		startRowNo = (pageNo - 1) * rowsPerPage + 1;
		endRowNo = pageNo * rowsPerPage;
		
//		// 이전 페이지 존재 여부
//		//
//		hasPreviousPage = startPageNo == 1 ? false : true;
//		if(hasPreviousPage == false) {
//			if(pageNo != startPageNo) {
//				hasPreviousPage = true;
//			}else hasPreviousPage = false;
//		}
//		
//		// 다음 페이지 존재 여부
//		hasNextPage = 
	}
	
	public static JSONObject pageToJson(PagingVo pvo) {
		JSONObject jobj = new JSONObject();
		jobj.put("totalRows", pvo.getTotalRows());
		jobj.put("totalPageNo", pvo.getTotalPageNo());
		jobj.put("totalGroupNo",pvo.getTotalGroupNo());
		jobj.put("startPageNo", pvo.getStartPageNo());
		jobj.put("endPageNo", pvo.getEndPageNo());
		jobj.put("pageNo", pvo.getPageNo());
		jobj.put("pagesPerGroup", pvo.getPagesPerGroup());
		jobj.put("groupNo",pvo.getGroupNo());
		jobj.put("rowPerPage", pvo.getRowsPerPage());
		jobj.put("startRowNo", pvo.getStartRowNo());
		jobj.put("endRowNo", pvo.getEndRowNo());
		
		return jobj;
	}
	
	public static PagingVo jsonToPage(JSONObject json) {
		PagingVo pvo = new PagingVo(json.getInt("totalRows"), json.getInt("pageNo"));
		
		return pvo;
	}
	
	public static void navigatoer(PagingVo pvo) {
		System.out.println("----------------------  네비게이터------- ");				
		for(int i = pvo.getStartPageNo(); i <= pvo.getEndPageNo(); i++ ) {
			if(i == pvo.getPageNo()) {
        		System.out.print("(" + i + ") ");
        	}
        	else System.out.print(i + " ");
		}
	}

}







