function delFruit(fid) {
    if (confirm("Are you sure to delete the fruit?")) {
        // 当前窗口.地址栏对象.href属性 赋值为'del.do?fid=' + fid， 顺便把fid带过去
        window.location.href = 'fruit.do?fid=' + fid+'&operate=delete';
    }
}

function page(pageNo){
    window.location.href = 'fruit.do?pageNo='+pageNo;
}