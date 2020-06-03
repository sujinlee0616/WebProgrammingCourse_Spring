package com.sist.web;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.CategoryVO;
import com.sist.dao.FoodService;

@RestController
@CrossOrigin(origins="http://localhost:3000") 
// CORS 허용: WebStorm에서 짠 React 소스는 3000번 이용 ==> @CrossOrigin 어노테이션 써서 사용.
// MongoDB 이용하는 3355도 허용하고 싶다면 @CrossOrigin(origins="http://localhost:3000,http://localhost:3355") 이렇게  하면 됨.
// ※ CORS: Cross-origin resource sharing.
public class JSONController {
	@Autowired
	private FoodService service;
	
	@RequestMapping("category.do")
	public String category()
	{
		List<CategoryVO> list=service.categoryListData();
		JSONArray arr=new JSONArray();   // 데이터 여러개 들어가야 ==> 배열로 잡음 ==> []
		for(CategoryVO vo:list)
		{
			JSONObject obj=new JSONObject();   // 오브젝트를 생성 
			obj.put("title", vo.getTitle());   // put 해서 {} 오브젝트에 값 추가 
			obj.put("subject",vo.getSubject());  // put 해서 {} 오브젝트에 값 추가 
			arr.add(obj);  // 배열에 오브젝트 추가 ==> [{title:'',subject:''},...,{title:'',subject:''}] 
		}
		return arr.toJSONString();
	}
	
	// WebStorm에서 node 서버 돌려줘야함 (npm start) 
	// WebStorm에서 sist-spring-project > App.js에 아래와 같이 '내IP:8079/web/category.do' 에서 데이터 가져오겠다고 코딩했었음 
	/* useEffect(()=>{
	    axios.get('http://xxx.xxx.xxx.xxx:8079/web/category.do').then((result)=>{
	      setFood(result.data)
		    })
	   },[])
	 */
	// ※ 8079: 내 톰캣 Servers 폴더 > server.xml에서 포트 8079 쓴다고 해놨음 
}
