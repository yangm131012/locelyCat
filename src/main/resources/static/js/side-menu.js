/**
 * 
 */

initMenu();

function initMenu() {
	 $.ajax({  
	     url:"/permissions/current",  
	     type:"post",  
	     async:false,
	     success:function(data){
	    	 if(!$.isArray(data)){
	    		 location.href='/logout';
	    		 return;
	    	 }
             var menu = $("#side-menu")//.children("li:last-child");
             $.each(data, function(i,item){ 
            	 var li = $("<li></li>");
            	 var a;
            	 var arrow = $("<span class='fa arrow'></span>");
            	 if (item.parentId == 0)
            	 {
            		 a = $("<a class = 'J_menuItem' href='"+item.href+"'></a>");
            	 }
            	 else
            	 {
            		 a = $("<a href='#'></a>");
            	 }
            	 var i = $("<i class='"+item.css+"'></i>")
            	 var span = $("<span class='nav-label'>"+item.name+"</span>");
            	 if (item.child != null && item.child.length >0)
            	 {
                 a.append(arrow);
            	 }
            	 a.append(i);
            	 a.append(span);
            	 li.append(a);
            	 menu.append(li);
            	 setChild(li,item.child);
             });
	     }
	 });

}

//设置子菜单元素
function setChild(parentElement, child)
{
	if(child != null && child.length > 0){
	$.each(child,function(j,item2){
		var arrow = $("<span class='fa arrow first'></span>");
		var ul = $("<ul class='nav nav-second-level'></ul>");
		var li = $("<li></li>");
		var a = $("<a class='J_menuItem' href="+ item2.href + "></a>");
		var i = $("<i class='"+item2.css+"'></i>")
   	    var span = $("<span class='nav-label'>"+item2.name+"</span>");
		a.append(i);
	    a.append(span);
	   	 if (item2.child != null && item2.child.length >0)
		 {
	     a.append(arrow);
		 }
		li.append(a);
		ul.append(li);
		parentElement.append(ul);
		setChild2(li,item2.child);
		
	});
	}
	
}

//设置第三层子菜单元素
function setChild2(parentElement, child)
{
	if(child != null && child.length > 0){
	$.each(child,function(j,item3){
		var arrow = $("<span class='fa arrow first'></span>");
		var ul = $("<ul class='nav nav-third-level'></ul>");
		var li = $("<li></li>");
		var a = $("<a class='J_menuItem' href="+ item3.href + "></a>");
   	    var span = $("<span class='nav-label'>"+item3.name+"</span>");
	    a.append(span);
	   	 if (item3.child != null && item3.child.length >0)
		 {
	     a.append(arrow);
		 }
		li.append(a);
		ul.append(li);
		parentElement.append(ul);
		setChild3(li,item3.child);
	});
	}
	
}

//设置第四层子菜单元素
function setChild3(parentElement, child)
{
	if(child != null && child.length > 0){
	$.each(child,function(j,item4){
		
		var ul = $("<ul class='nav nav-fifth-level'></ul>");
		var li = $("<li></li>");
		var a = $("<a class='J_menuItem' href="+ item4.href + "></a>");
   	    var span = $("<span class='nav-label'>"+item4.name+"</span>");
	    a.append(span);

		li.append(a);
		ul.append(li);
		parentElement.append(ul);
	});
	}
	
}


getRoleName();
function getRoleName() {
	 $.ajax({  
	     url:"/users/current",  
	     type:"post",  
	     async:false,
	     success:function(data){
//	    	 if(!$.isArray(data)){
//	    		 location.href='/login.html';
//	    		 return;
//	    	 }
             var roleName = $("#roleName");
             roleName.html('<strong class="font-bold" style="color:#fff;">' + data.userName + '</strong><b class="caret"></b>');
	     }
	 });
}
