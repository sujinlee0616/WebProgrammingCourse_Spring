<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto;
   width:800px;
}
h1 {
  text-align: center;
}
</style>
<!-- React -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- [React] 
	1. props
	 - properties. 속성값을 의미함.
	 - ★부모 컴포넌트가 자식 컴포넌트에게 주는 값.★ 
	 - 변경X: 자식 컴포넌트에서는 props 를 받아오기만하고, 받아온 props 를 직접 수정 할 수 는 없다.
	 - ex) <App name="홍길동">  
	   : Java에서의 new App("홍길동"); 과 같다.
	   : 값을 바꿀 수 없다. 
	2. state  
	 - 컴포넌트 내부에서 선언.
	 - 변경O: 내부에서 값을 변경 할 수 있다. 
-->
<!-- [React]
	<App/>
	1. contructor(): 초기화 (변수 설정)
	2. render(): 데이터를 받아서 화면 출력(HTML)
	3. componentDidMount(): 완료 => 브라우저에 출력 
	4. 화면변경: 완료가 된 다음 화면을 변경하려면 다시 render() 해야 (re-rendering) 
	   ==> state를 변경한다. (setState()) 
 -->
 <!-- [함수 호출방식]
	1. 기본
 		function H(){}
 	2. 익명함수
 		var H=function(){}
 	3. arrow function
 		var H=()=>{}
 		: 'function'이라는 글자가 사라지고 대신 ()뒤에 =>이 붙는다.
 
  -->
</head>
<body>
	<div class="container" id="root"></div>
	<script type="text/babel">

	

	// ====================== [App]: 아래의 3개 클래스를 조립★★★  ======================
	// ★Java에서의 main과 비슷★ 
	// ==> ☆App 안에서 모든 게 다 실행되고 모든 것을 다 처리한다. 얘가 전체를 다 관리한다.☆ 
	class App extends React.Component{

		// [생성자]
		constructor(props){
			super(props); // 속성값 받기 
			// 서버에서 들어오는 데이터를 받아서 저장 
			this.state={
				music:[],
				ss:''
				// music:[]에 setState가 값을 채움 
				// list에 50개 데이터가 들어옴 ==> music: 배열로 받자 
			}
			// React에서 event 등록
			this.handlerUserInput=this.handlerUserInput.bind(this);
		}

		// []
		handlerUserInput(ss)
		{
			this.setState({ss:ss});
		}

		// []
		componentDidMount()
		{
			// 서버 연결
			axios.get('http://localhost/web/main/music_data.do').then((result)=>{
				console.log(result.data);
				// 저장 
				// this.state.music=result.data;
				// render를 호출해야 (다시 render해야) ==> setState해서 다시 render하면 됨  
				this.setState({music:result.data}); 
				
			})
			// http://localhost/web/main/music_data.do : JSON 출력되고 있음 
		}

		// [화면출력] (HTML)
		render(){
			return(
				// HTML - XML 형식으로 코딩해야. 여닫는거 잘 해야함. 
				<div className="row">
					
					{/* [H 호출] */} 
					{/* : H의 return 값을 가지고 온다. */} 
					<H/>

					{/* [SearchBar 호출]  */} 
					{/* : SearchBar의 return 값을 가지고 온다. */} 
					<SearchBar ss={this.state.ss} onUserInput={this.handlerUserInput}/>
					{/*                           ================================== */}
					{/*							   props로 이벤트도 넘길 수 있다.           */} 

					<div style={{"height":"30px"}}></div>

					{/* [MusicTable 호출]  */} 
					{/* : MusicTable의 return 값을 가지고 온다.  */} 
					<MusicTable music_table={this.state.music} ss={this.state.ss}/>
					{/*         ============================  ================== */} 
					{/*			props임				    		props            */} 
					
				</div>
			)
		}
	}


	// ====================== [H] - 제목  ======================
	// 계속 호출됨 ==> 동작할 때마다 제목의 색상이 바뀜 
	const H=()=>{
		const color=['red','green','blue','yellow','pink'];
		let rand=parseInt(Math.random()*5); // 0~4까지 나옴
		let s={"color":color[rand]};
		return(
			<h1 className="text-center" style={s}>뮤직 Top 200</h1>
		)
	}

	// ====================== [H2] - React.memo  ======================
	// 한 번만 호출함.즉, 위의 H에서 더 개선된 버젼. 근데 지금 우리가 쓰는 라이브러리가 버젼이 낮아서 사용불가 ㅠ_ㅠ
	// 참고 글) React.memo() 현명하게 사용하기
	// https://ui.toast.com/weekly-pick/ko_20190731/ 
	/* const H2=React.memo(()=>{
		const color=['red','green','blue','yellow','pink'];
		let rand=parseInt(Math.random()*5); // 0~4까지 나옴
		let s={"color":color[rand]};
		return(
			<h1 className="text-center" style={s}>뮤직 Top 200</h1>
		)
	}); */


	// ====================== [MusicTable] - 음악 리스트  ======================
	// - MusicTable은 1) 자기내용 + 2) MusicRow값 출력한다.
	// - 1) 자기내용: 테이블 첫 줄 (제목줄)
	// - 2) MusicRow값: rows[] 배열에다가 MusicRow를 push하고, 이 rows 배열을 자기 table의 <tbody>안에다가 출력했음 
	class MusicTable extends React.Component{
		// 출력
		render(){
			let rows=[];
			
			this.props.music_table.forEach((m,key)=>{  
				// m: [{key:value,...,key:value},{...},{...}] 에서 {} 하나를 m이라고 했음 
				if(m.title.indexOf(this.props.ss)===-1) // 영화제목에 내가 입력한 키워드가 존재하지 않는다면 
				{
					return; // 리턴시킴 ===> forEach 구문으로 다시 돌아감 ==> rows 배열에 추가하지 않음 
				}
				rows.push(<MusicRow music={m}/>);
				//                  ========= props
				// rows 배열에 <MusicRow>를 넣어놓고 (MusicRow class의 return값을 넣음) 
				// 이걸 <tbody>안에다가 {rows}해서 넣는다 
			})
			return(
				<table className="table">
					<thead>
						<tr className="danger">
							<th className="text-center">순위</th>
							<th className="text-center">등폭</th>
							<th className="text-center"></th>
							<th className="text-center">노래명</th>
							<th className="text-center">가수명</th>
						</tr>
					</thead>
					<tbody>
						{rows}
						{/* ★★★★★ */} 
					</tbody>
				</table>
			)
		}
	}

	// ====================== [MusicRow] - 음악 리스트의 내용 ======================
	// MusicTable의 <tbody>안에 출력됨 
	class MusicRow extends React.Component{
		render()
		{
			return(
				<tr>
					<td className="text-center">{this.props.music.mno}</td>
					<td className="text-center">
						{/* [주석]: 이렇게 주석줘야함... */}
						{/* [다중조건문] */}
						{
							this.props.music.state==="상승" && 
							<span style={{"color":"red"}}>▲{this.props.music.idcrement}</span>
						}
						{
							this.props.music.state==="하강" && 
							<span style={{"color":"blue"}}>▼{this.props.music.idcrement}</span>
						}
						{
							this.props.music.state==="유지" && 
							<span style={{"color":"grey"}}>-0</span>
						}
					</td>
					<td className="text-center">
						<img src={this.props.music.poster} width="30" height="30"/>
						{/* src값은 변수라서 "" 안에 넣지 않는다!!! */}
					</td>
					<td>
						<a href={"detail.do?mno="+this.props.music.mno}>{this.props.music.title}</a>
						{/* href 쓴 방법 주의!!! */}
						{/* <a href="detail.do?mno={this.props.music.mno}"> <== 이렇게 쓰면 안 됨!!! */}
						{/* <a href={"detail.do?mno="+this.props.music.mno}> <== 이렇게 써야함!!! */}
					</td>
					<td>{this.props.music.singer}</td>
				</tr>
			)
		}
	}

	// ====================== [검색창] ======================
	class SearchBar extends React.Component{
		constructor(props)
		{
			super(props);
			this.onChange=this.onChange.bind(this);
		}
		onChange(e)
		{
			this.props.onUserInput(e.target.value);
			console.log('SearchBar > onChange 함수');
		}
		render(){
			return(
				<input type="text" size="30" className="input-sm" placeholder="검색"
					value={this.props.ss} onChange={this.onChange}
				/>
			)
		}
	}

	// ====================== [ReactDOM render] : App을 render ======================
	// - div #root에다가 detail 리턴값 포함시킨다.
	ReactDOM.render(<App/>,document.getElementById('root'));
	// <App/> : class App의 html을 가져옴 
 	// 위의 코드는 아래의 두 코드와 동일함 
	// String data=new App().render();
	// $('root').html(data); 

	</script>
</body>
</html>







