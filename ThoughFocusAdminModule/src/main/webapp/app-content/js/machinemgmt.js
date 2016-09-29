$(document).ready(function(){
	  $('.selectpicker').selectpicker({
	    width:'100px'
	  });

	  $('#btn-search').on('click',function(e){
	  	e.preventDefault();
	    var search_engine = $('#ddlsearch option:selected').val(),
	        search_key = $('#txtkey').val(),
	        $sresult = $('#sresult'),
	    	label = '<span class="label label-danger">URL</span>';
	    if(search_engine && search_key){
	      switch (search_engine) {
	        case 'google':
	          $sresult.html(label + ': http://www.google.com/search?q=' + search_key);
	          window.location.href = ('http://www.bing.com/search?q=' + search_key);
	          break;
	        case 'bing':
	          $sresult.html(label + ': http://www.bing.com/search?q=' + search_key);
	          break;
	        case 'yahoo':
	          $sresult.html(label + ': http://search.yahoo.com/search?p=' + search_key);
	          break;
	      }
	    }
	  });
	});
