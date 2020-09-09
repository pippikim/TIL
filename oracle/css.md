```css
=======
@charset "UTF-8";
html{
	overflow-y: scroll;
}

	body {
			font: 16px "Kakao","Noto Sans KR","나눔고딕",NanumGothic,"맑은고딕","Malgun Gothic","돋움",Dotum,"Helvetica Neue",Helvetica,AppleSDGothicNeo,sans-serif,"Apple Color Emoji","Segoe UI Emoji",NotoColorEmoji,"Segoe UI Symbol","Android Emoji",EmojiSymbols;
			color:#616161;
			background-color: #F5F5F5;

		}
		a {
			color:#616161;
			text-decoration: none;
		}
		#header{
			width: 100%;
			height: 88px;
			line-height: 45px;
			background-color: #212121; 
			position: -webkit-sticky; 
			position: sticky;
			top: 0;
			z-index: 999;
			transition:1s ease;
			box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0
			rgba(0, 0, 0, 0.12);
			
		}
		
		.hidden{
			display: none;
		}
		#header #logoBox{
			width: 100px;
			position: absolute;
			left: 10px;
			top: 7px;
		}
		#header #logoBox img{
			width: 162.9px;
			height: 26.5px;
		}
		.aux{
			width: 1080px;
			margin:auto;
			position: relative;
		}
		#header #gnb{
			height: 43px;
			width: 700px;
			/*background-color: red;*/
			font-size: 20px;
			position: absolute;
			top: 40px;

		}
		#gnb ul{
			max-width: 700px
			height: 43px;
			display: flex;
		}
		#gnb .nav{
			float: left;
			margin-left: 24px;
			display: flex;
			box-sizing: border-box;
			line-height: 43px;
			text-align: center;
			
			
		}
		#gnb .nav>a{
			display: block;
			color: #FFE0B2;
			border-bottom: 5px solid #212121; 
			/*font-weight: bold;*/
		}
		#gnb .nav>a:hover{
			color: #FF907C;
		}
		#gnb .nav.on>a{
			color: #FF907C;
			border-bottom-color: #FF907C;
		}

		
		#header #loginBox{
			width: 80px;
			/*background-color: red;*/
			position: absolute;
			top: 5px;
			right: 10px;
			font-size: 15px;
			line-height: 30px;
		}

		#loginBox #loginBtn{
			width: 80px;
			height: 30px;
		
		}
		.btn{
			background-color: #FF907C;
			text-align: center;
			border-radius: 3px;
			color: #212121;
			cursor: pointer;
		}


		/**/

		#loginBox #profileBox {
	height:40px;
	position: absolute;
	right:0;
}
#profileBox {
	width:140px;
	right:10px;
}

#profileBox img {
	width:38px;
	height:38px;
	border-radius: 20px;
	box-shadow: 0 12px 15px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
	margin-right:5px;
	border:1px solid #666;
	position: absolute;
	cursor: pointer;	
	right:10px;
	top:0px;
}

#profilePopup{
	width: 136px;
	background-color: #4D525A;
	border:2px solid #4D525A;
	display: none;
	position: absolute;
	top:50px;
	right: 0px;
	font-weight:900;
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
	z-index: 99;
}	

#profilePopup::before{
	content: " ";
	width: 0px;
	height: 0px;
	background: none;
	display: block;
	border-left: 10px solid transparent;
	border-right: 10px solid  transparent;
	border-bottom: 10px solid #4D525A;
	position: absolute;
	top: -10px;
	right: 20px;
}

#loginBox #profileList{
	text-align: right;
	
}


#profileList li.profile{
	width: 126px;
	height: 68px;
	padding-right: 10px;
	font-size: 18px;
	line-height: 68px;
	transition: .4s ease;
	position: relative;

}

#profileList li.profile a{
	height: 68px;
	font-size: 18px;
	display: inline-block;
	color: rgb(216, 171, 83);
	text-decoration: none;

}

#profileList li.profile form button{
	height: 68px;
	display: inline-block;
	padding: 0;
	border: none;
	color: rgb(216, 171, 83);
	font: 18px "Kakao";
	font-weight: bold;
	background: transparent;
}

#profileList li.profile span.open_door{
	height: 58.5px;
	width: 26.75px;
	visibility: hidden;
	opacity: 0;
	transition: .4s ease-in-out;
	transform: translateX(-9.25px);
	background-image: url("../img/door-open.png");
	background-size: contain;
	background-repeat: no-repeat;
	text-indent: 99999px;
	overflow: hidden;
	position: absolute;
	right: 102.81px;
	bottom: 4.75px;

}

#profileList li.profile span.close_door{
	height: 49px;
	width: 27px;
	visibility: hidden;
	opacity: 0;
	transition: .4s ease-in-out;
	transform: translateX(-27px);
	background-image: url("../img/door-close.png");
	background-size: contain;
	background-repeat: no-repeat;
	text-indent: 99999px;
	overflow: hidden;
	position: absolute;
	right: 86.25px;
	bottom: 9.5px;

}

#profileList li.profile .fa-door-open{
	color: #2D230D;
}

#profileList li.profile:hover{
	background-color: rgb(216, 171, 83);

}

#profileList li.profile:hover a , #profileList li.profile:hover form button{
	color: #2D230D;
	font-weight: bold;
	cursor: pointer;
}

#profileList li.profile:hover span{
	visibility: visible;
	opacity: 1;
	transform: translateX(0);

}

		/**/
		#header #searchBox{
			width: 350px;
			height: 30px;
			/*background-color: blue;*/
			position: absolute;
			right: 0;
			top: 49px;

		}
	
		#searchBox .inp_txt{
			width: 300px;
			height: 30px;
			border:none;
			outline: none;
			background-color: #fff;
			/*position: absolute;*/
			
			padding:0 10px 0 40px;
			font-size: 16px;
			border-radius: 2px;
		}
		#searchBox span.btn_search{
			display: block;
			color: #424242;
			line-height: 49px;
			position: absolute;
			top: 0;
			left: 10px;
			font-size: 18px;
		}

		#policy {
			width:800px;
			height:60px;
			display: inline-block;
		}
	#footer {
		display: block;
	width:100%;
	background: #212121;
	height:60px;
	line-height: 60px;
	text-align: center;
	
	}

#footer.fixed {
	position: fixed;
	bottom:0;
}
#footer address {
	width:190px;
	height:60px;
	display: inline-block;
	color: #FFE0B2;
}



#footer a {
	color:#fff;
	text-decoration:none;
}
#footer a:hover {
	color: #FFE0B2;
	text-decoration: underline;
}
a.btn {
	text-decoration: none;
}
#footer address a {
	font-weight: 400;
}

#policy ul {
	text-align: center;
}

#policy li {
	display: inline;
	border-left:1px solid #ddd;
	margin-right:9px;
	padding-left: 8px;
}

#policy li:first-child {
	border:none;
}

#policy strong {
	font-weight:400;
}

#content {
	width:100%;
	min-height: 400px;
	padding: 50px 0 50px 0;
	position: relative;
	/*margin-top: 88px;*/

>>>>>>> master
```


