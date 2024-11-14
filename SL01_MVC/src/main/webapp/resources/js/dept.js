console.log("Dept Module........");

const deptService = (function(){
	
	// http://localhost/scott/dept/new 새로운 부서 추가
	// data:JSON.stringify(dept) js 객체 -> JSON
	function add(dept, callback, error){
		console.log("> deptService.add()");
		
		$.ajax({
			type:'post',
			url:'/scott/dept/new',
			data:JSON.stringify(dept),
			contentType : "application/json; charset=utf-8",
			cache:false,
			beforeSend:function(xhr){
				console.log("add.beforeSend test");
			},
			success:function(result,status,er){
				if(callback){
					callback(result);
				} // if
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				} // if
			}
		});
	} // add
	
	return {
		add:add
	};
})();

const deptRemove = (function(){
	
	function remove(deptno, callback, error){
		console.log("> deptRemove.remove()");
		
		$.ajax({
			type:'post',
			url:'/scott/dept/remove',
			data:JSON.stringify(deptno),
			contentType : "application/json; charset=utf-8",
			cache:false,
			beforeSend:function(xhr){
				console.log("remove.beforeSend test");
			},
			success:function(result,status,er){
				if(callback){
					callback(result);
				} // if
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				} // if
			}
		});
	}
	
	return {
		remove:remove
	};
})();