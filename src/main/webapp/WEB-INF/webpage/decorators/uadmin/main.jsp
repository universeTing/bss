<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
.sc-calendar{width:500px;height:500px;text-align:center;font-family:"Microsoft Yahei";color:#4a4a4a;box-shadow:2px 4px 5px #bdbdbd;border-width:1px 0 0 1px;border-color:#e6e4e0;border-style:solid;float:left;margin-right:20px;-moz-user-select:none;-webkit-user-select:none;-ms-user-select:none;user-select:none;-webkit-text-size-adjust:none;font-size:16px}.sc-header{height:35px;border-bottom:0}.sc-body{height:93%;clear:both;}.sc-week{height:12%;font-weight:400;font-size:20px;color:#4a4a4a}.sc-days{height:88%}.sc-item{height:20%;float:left;font-weight:600;color:#565555;width:14.285%;padding-top:10px;background-color:#fff;border-width:0 0 1px 1px;border-color:#f1ebe4;border-style:solid;box-sizing:border-box}.item-nolunar{padding-top:20px}.sc-item:nth-child(7n) .day,.sc-item:nth-child(7n+6) .day{color:rgba(224,8,8,0.74)}.sc-vocation{background-color:#ffebec}.sc-mark{background-color:#e5fbfa}.sc-vocation:before{content:'休';display:block;position:absolute;font-size:.7em;width:1.2em;font-weight:100;color:white;background-color:#e00808;margin-top:-10px}.sc-othermenth{color:#c1c0c0!important}.sc-othermenth .day,.sc-othermenth .lunar-day{color:#c1c0c0!important}.sc-active-day,.sc-selected{border:1px solid orange}.sc-today{background-color:orange;color:white;border:1px solid orange}.sc-item .day{font-size:1.5em}.sc-today .day{color:white!important}.sc-item .lunar-day{font-size:10px;font-weight:normal;overflow:hidden;text-overflow:ellipsis}.sc-festival .lunar-day{color:#e00808}.sc-week-item{height:100%;padding-top:2%;float:left;width:14.285%;background-color:#fbec9c;border-width:1px 0 1px 1px;border-color:#ece3b1;border-style:solid;box-sizing:border-box;overflow:hidden;text-overflow:ellipsis}.sc-item-small{font-size:10px!important}.sc-week-item:last-child{border-width:1px 1px 1px 1px}.sc-week-item:nth-child(7n),.sc-week-item:nth-child(7n+6){color:rgba(224,8,8,0.74)!important}.sc-actions{float:left;width:25%;padding:5px;height:100%;box-sizing:border-box}.sc-actions:last-child{float:right}.sc-actions-big{width:50%}@media screen and (max-width :500px){.sc-actions{width:50%}}.sc-header select{border-color:rgba(0,0,0,0);padding:.2em;-webkit-appearance:none;-moz-appearance:none;appearance:none;font-family:"Microsoft Yahei";color:#606060;font-size:13px}.sc-header input{border-color:rgba(0,0,0,0);padding:.2em;-webkit-appearance:none;-moz-appearance:none;appearance:none;font-family:"Microsoft Yahei";color:#606060}.sc-actions div{display:inline-block;vertical-align:bottom;width:20px;padding-bottom:5px;font-size:1.5em;line-height:.9em}.sc-return-today{display:block;background-color:#f5f5f9;border-radius:2px;width:60px;font-size:.8em;padding:.3em;margin:auto}.sc-time{display:block;margin-top:3px;font-size:.8em}
ul#newInfo li{color:#000;border-bottom:1px solid #eee;padding:15px 0;}
ul#newInfo li font{background-color: #FF9002;color:#fff;padding:2px;margin-right:10px;font-size:10px;}
ul#newInfo li span{float: right;color:#ccc;font-size:10px;}
</style>

  		<div class="absolute left">
			<div class="uinfo">
				<img src="${appPath}/${fns:getUser().portrait}" style="height:143px" class="head" onload="this.style.left=-(this.width-145)/2+'px'" onerror="this.src='${staticPath}/common/img/user.png'"/>
				<p class="name">${fns:getUser().username}</p>
				<p class="job">${fns:getUser().realname}</p>
			</div>
			<div class="tip">
				<div class="am-u-sm-4">
					<p class="n mess">0</p>
					<p>消息</p>
				</div>
				<div class="am-u-sm-4">
					<p class="n flow">0</p>
					<p>流程</p>
				</div>
				<div class="am-u-sm-4">
					<p class="n alt">0</p>
					<p>提醒</p>
				</div>
			</div>
			<!-- <div class="edit">
				<a href="javascript:">编辑</a>
			</div> -->
		</div>
		<div class="absolute right">
			<ul class="tabs-t">
				<li class="active"><div id="echarts-title">最新动态</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active">
					<ul id="newInfo">
						<li><font>资金使用</font>就好打了结合法拉赫纷乱回复了敬爱的好了防寒阿尔法<span>防城&nbsp;2018-2-12</span></li>
						<li><font>资金使用</font>就好打了结合法拉赫纷乱回复了敬爱的好了防寒阿尔法<span>防城&nbsp;2018-2-12</span></li>
						<li><font>资金使用</font>就好打了结合法拉赫纷乱回复了敬爱的好了防寒阿尔法<span>防城&nbsp;2018-2-12</span></li>
						<li><font>资金使用</font>就好打了结合法拉赫纷乱回复了敬爱的好了防寒阿尔法<span>防城&nbsp;2018-2-12</span></li>
						<li><font>资金使用</font>就好打了结合法拉赫纷乱回复了敬爱的好了防寒阿尔法<span>防城&nbsp;2018-2-12</span></li>
					</ul>
				</li>
			</ul>
		</div>
		
		<div class="absolute bottom">
			<ul class="tabs-t">
				<li class="active"><div id="echarts2-title">日历</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active" style="padding:0;">
					<div id="echarts2">
							
					</div>
				</li>
			</ul>
		</div>
		<hr>
		<script src="${staticPath}/uadmin/js/main.date.js"></script>
		