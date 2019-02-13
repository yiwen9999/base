function pageCreate(currentPage, pageNum) {
    if (pageNum > 1) {
        var currentPage = currentPage || Number(1);
        var pageNum = pageNum || Number(5);

        $(".page_div").show();
        $(".page_btn").show();
        $(".page_div span").show();

        //给每个button赋值（第一个默认为1）
        $("#btn2").text(currentPage - 2);
        $("#btn3").text(currentPage - 1);
        $("#btn4").text(currentPage);
        $("#btn5").text(currentPage + 1);
        $("#btn6").text(currentPage + 2);
        $("#btn7").text(pageNum);

        //可改变当前页的button样式
        $("#btn4").css("background-color", "#F19707");

        //先处理"上一页"和"下一页"的情况
        if (currentPage == 1) {//如果当前页为首页
            $("#prePage").hide();
        }
        if (currentPage == pageNum) {//如果当前页为末页
            $("#sufPage").hide();
        }

        if (currentPage <= 3) {//处理当前页小于等于3的特殊情况
            $("#prePoint").hide();
            $("#btn1").hide();
        } else if (currentPage == 4) {//当前页是4还需要hide掉第一个省略号按钮（！重要）
            $("#prePoint").hide();
        }
        if (currentPage == 1) {//当前页是1还需要hide掉第二第三个按钮
            $("#btn2").hide();
            $("#btn3").hide();
        } else if (currentPage == 2) {//当前页是2则也需要hide掉第二个按钮（此时为-1）
            $("#btn2").hide();
        }

        //最末端的特殊情况处理和最前端是一样的
        if (currentPage >= pageNum - 2) {
            $("#sufPoint").hide();
            $("#btn7").hide();
        } else if (currentPage == pageNum - 3) {
            $("#sufPoint").hide();
        }

        if (currentPage == pageNum) {
            $("#btn5").hide();
            $("#btn6").hide();
        }
        if (currentPage == pageNum - 1) {
            $("#btn6").hide();
        }
        pageTurning();
    } else
        $(".page_div").hide();
}

function pageTurning() {
    var btns = document.querySelectorAll(".page_btn");
    btns.forEach(function (btn, index) {
        if (btn.id === 'prePage') {//up
            btn.onclick = function () {
                searchList(currentPage - 2);
            };
        } else if (btn.id === 'sufPage') {//nex
            btn.onclick = function () {
                searchList(currentPage);
            };
        } else {
            btn.onclick = function () {
                searchList(parseInt(btn.innerText) - 1);
            };
        }
    });
}