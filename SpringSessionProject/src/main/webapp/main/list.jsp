<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- React Script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container" id="root"></div>
  <script type="text/babel">
	const H=()=>{
		const color=['red','green','blue','yellow','pink'];
		let rand=parseInt(Math.random()*5)
		let s={"color":color[rand]}
		return (
			<h1 className="text-center" style={s}>뮤직 Top 200</h1>
		)
	}
	/*const H2=React.memo(()=>{
		const color=['red','green','blue','yellow','pink'];
		let rand=parseInt(Math.random()*5)
		let s={"color":color[rand]}
		return (
			<h1 className="text-center" style={s}>뮤직 Top 200</h1>
		)
	});*/
	class App extends React.Component{
		// 생성자 만들기
		constructor(props){
			// 속성값받기
			super(props);
			// 서버에서 들어오는 데이터를 받아서 저장하기
			this.state={
				music:[],
				ss:''
			}
			// react에서 이벤트 등록
			this.handleUserInput=this.handleUserInput.bind(this);
		}
		handleUserInput(ss)
		{
			this.setState({ss:ss});
		}
		
		componentDidMount()
		{
			// 서버 연결하기
			axios.get('http://localhost/web/main/music.do').then((result)=>{
				console.log(result.data);
				//this.state.music=result.data;
				this.setState({music:result.data})
			})
		}
		
		// 화면출력담당 (HTML이 들어온다)
		render(){
			return (
				<div className="row">
				  <H/>
				  <SearchBar ss={this.state.ss} onUserInput={this.handleUserInput}/>
				  <div style={{"height":"30px"}}></div>
				  <MusicTable movie={this.state.music} ss={this.state.ss}/>
				</div>
			)
		}
		
	}
	class MusicTable extends React.Component{
		render(){
			let rows=[];
			this.props.movie.forEach((m,key)=>{
				if(m.title.indexOf(this.props.ss)===-1)
				{
					return;
				}
				rows.push(<MusicRow music={m}/>)
			})
			return (
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
				  </tbody>
				</table>
			)
		}
	}
	class MusicRow extends React.Component{
		render()
		{
			return (
				<tr>
				  <td className="text-center">{this.props.music.mno}</td>
				  <td className="text-center">
					{/*다중 조건문*/}
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
					  <span style={{"color":"gray"}}>-</span>
					}
				  </td>
				  <td className="text-center">
				    <img src={this.props.music.poster} width="30px" height="30px"/>
				  </td>
				  <td><a href={"detail.do?mno="+this.props.music.mno}>{this.props.music.title}</a></td>
				  <td>{this.props.music.singer}</td>
				</tr>
			)
		}
	}
	class SearchBar extends React.Component{
		constructor(props)
		{
			super(props);
			this.onChange=this.onChange.bind(this);
		}
		onChange(e)
		{
			this.props.onUserInput(e.target.value);
		}
		render(){
			return (
			  <input type="text" size="30" className="input-sm" placeholder="검색"
				value={this.props.ss} onChange={this.onChange}
			  />
			)
		}
	}
	ReactDOM.render(<App/>,document.getElementById('root'));
	
  </script>
</body>
</html>