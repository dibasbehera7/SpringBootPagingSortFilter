$(document).ready(function(){	
	
	$("#txt_search").keyup(function(){
		
		if($("#txt_search").val().length > 3){
			
				var search = $(this).val();
				if(search != ""){
					
				$.ajax({
					type: "GET",
					url : "/api/filterByTitle?title=Life",
					data : { page:0, size:5},
					dataType: 'json',
					success:function(response){
					
					var len = response.content.length;
					$("#searchResult").empty();
					
						for( var i = 0; i<len; i++){
							var id = response.content[i]['id'];
							var title = response.content[i]['title'];
							$("#searchResult").append("<li value='"+id+"'>"+title+"</li>");
						}
						
						// binding click event to li
			            $("#searchResult li").bind("click",function(){
						 $.ajax({
							            type : "GET",
							            url : "/api/pageablebyId",
							            data: {
											id:this.value,
							                page: 0, 
							                size: 5
							            },
							            success: function(response){
							              $('#bookTable tbody').empty();
							              // add table rows
							              $.each(response.content, i => {  																
											 	let bookRow = '<tr>';
												bookRow += '<td>' + response.content[i]['id'] + '</td>';				
												bookRow += '<td>' + response.content[i]['title'] + '</td>';				
												bookRow += '<td style="text-align: center">' + response.content[i]['pages'] + '</td>';					
												if(response.content[i]['instock'] == true)
												bookRow += '<td class="table-success" style="text-align: center">' + 'Available' + '</td>';					
												if(response.content[i]['instock'] == false)
												bookRow += '<td class="table-danger" style="text-align: center">' + 'Not Available' + '</td>';
												if(response.content[i]['instock'] == null)
												bookRow += '<td class="table-warning" style="text-align: center">' + 'Out of Stock' + '</td>';
												bookRow += '</tr>';
										        $('#bookTable tbody').append(bookRow);
							              });              
							              /*if ($('ul.pagination li').length == 0){
							              	  // build pagination list at the first time loading
							                  buildPagination(response.totalPages);
							              }*/
											$("#searchResult").empty();
											$(".pagination").empty();
							            },
							            error : function(e) {
							              //alert("ERROR: ", e);
							              console.log("ERROR: ", e);
							              totalPages = 0;
							            }
							        });  
							
							
			            });
					}
				});
					
				}
		}else{
			getBooks(0);
		}
				
		
	});
	
	
    function getBooks(page, size){
    	size = (typeof size !== 'undefined') ?  size : 5
        $.ajax({
            type : "GET",
            url : "/api/custom/pageable",
            data: { 
                page: page, 
                size: size
            },
            success: function(response){
              $('#bookTable tbody').empty();
              // add table rows
              $.each(response.books, (i, book) => {  
                let tr_id = 'tr_' + book.id;
                /*let bookRow = 
                            '<tr>' +
          	  						  '<td>' + book.id + '</td>' +
			                		  '<td>' + book.title + '</td>' +
			                		  '<td>' + book.pages + '</td>' +
			                      	   '<td>' + 'Yes' + '</td>' +
			                      '</tr>';*/
				let bookRow = '<tr>';
				bookRow += '<td>' + book.id + '</td>';				
				bookRow += '<td>' + book.title + '</td>';				
				bookRow += '<td style="text-align: center">' + book.pages + '</td>';					
				
				if(book.instock == true)
				bookRow += '<td class="table-success" style="text-align: center">' + 'Available' + '</td>';					
				if(book.instock == false)
				bookRow += '<td class="table-danger" style="text-align: center">' + 'Not Available' + '</td>';
				if(book.instock == null)
				bookRow += '<td class="table-warning" style="text-align: center">' + 'Out of Stock' + '</td>';
				bookRow += '</tr>';
							                      
                $('#bookTable tbody').append(bookRow);
              });              
              
              if ($('ul.pagination li').length == 0){
              	  // build pagination list at the first time loading
                  buildPagination(response.totalPages);
              }
            },
            error : function(e) {
              //alert("ERROR: ", e);
              console.log("ERROR: ", e);
              totalPages = 0;
            }
        });    	
    }
    
    function buildPagination(totalPages){
        // Build paging navigation
        let pageIndex = '<li class="page-item"><a class="page-link">Previous</a></li>';
        $("ul.pagination").append(pageIndex);
        
        // create pagination
        for(let i=1; i <= totalPages; i++){
      	  // adding .active class on the first pageIndex for the loading time
      	  if(i==1){
          	  pageIndex = "<li class='page-item active'><a class='page-link'>"
	  				+ i + "</a></li>"            		  
      	  } else {
          	  pageIndex = "<li class='page-item'><a class='page-link'>"
		  				+ i + "</a></li>"
      	  }
      	  $("ul.pagination").append(pageIndex);
        }
        
        pageIndex = '<li class="page-item"><a class="page-link">Next</a></li>';
        $("ul.pagination").append(pageIndex);
    }
    
    (function(){
    	// get first-page
    	getBooks(0);
    })();
        
    $(document).on("click", "ul.pagination li a", function() {
    	let val = $(this).text();
  	  	if(val.toUpperCase()==="NEXT"){
  	  		let activeValue = parseInt($("ul.pagination li.active").text());
  	  		let totalPages = $("ul.pagination li").length - 2; // -2 beacause 1 for Previous and 1 for Next 
  	  		if(activeValue < totalPages){
  	  			getBooks(activeValue); // get next page value
  	  			// remove .active class for the old li tag
  	  			let currentActive = $("li.active");
  	  			currentActive.removeClass("active");
  	  			// add .active to next-pagination li
  	  			currentActive.next().addClass("active");
  	  		}
  	  	}else if(val.toUpperCase()==="PREVIOUS"){
  	  		let activeValue = parseInt($("ul.pagination li.active").text());
  	  		if(activeValue > 1){
  	  			// get the previous page
  	  			getBooks(activeValue-2);
  	  			let currentActive = $("li.active");
  	  			currentActive.removeClass("active");
  	  			// add .active to previous-pagination li
  	  			currentActive.prev().addClass("active");
  	  		}
  	  	}else {
  	  		getBooks(parseInt(val) - 1 );
  	  		// add focus to the li tag
  	  		$("li.active").removeClass("active");
  	  		$(this).parent().addClass("active");
  	  	}
    });
});