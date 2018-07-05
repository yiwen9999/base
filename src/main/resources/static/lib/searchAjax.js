$(function(){
//当键盘键被松开时发送Ajax获取数据
		$('#text').keyup(function(){
			var keywords = $(this).val();
			if (keywords=='') { $('#word').hide(); return };
			$.ajax({
                type: 'POST',
                url: '/test/searchEngineer',
                data : {
                    "keywords" : keywords
                },
                dataType: 'json',
				// dataType: 'jsonp',
				// jsonp: 'cb', //回调函数的参数名(键值)key
				// jsonpCallback: 'fun', //回调函数名(值) value
				beforeSend:function(){
					$('#word').append('<div>正在加载。。。</div>');
				},
				success:function(data){
					
					$('#word').empty().show();
					if (data.data=='')
					{
						$('#word').append('<div class="error">未找到  "' + keywords + '"</div>');
					}
					$.each(data.data, function(index,engineer){
						$('#word').append('<div class="click_work">'+ engineer.name +'</div>');
					})
				},
				error:function(){
					$('#word').empty().show();
					$('#word').append('<div class="click_work">Fail "' + keywords + '"</div>');
				}
			})
		})
//点击搜索数据复制给搜索框
		$(document).on('click','.click_work',function(){
			var word = $(this).text();
			$('#text').val(word);
			$('#word').hide();
			// $('#texe').trigger('click');触发搜索事件
		})

	})