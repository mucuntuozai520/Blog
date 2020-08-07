var a_idx = 0;

$("body").click(function(e) {
    var a = new Array(
        "宇清最好看","宇清最漂亮","宇清最优美",
        "宇清最可爱","宇清最温柔","宇清最贤惠","宇清最善良",
        "宇清最优雅","宇清最清纯"
    );
    function c() {
        return "rgb(" + ~~ (255 * Math.random()) + "," + ~~ (255 * Math.random()) + "," + ~~ (255 * Math.random()) + ")"
    }
    var $i = $("<span/>").text(a[a_idx]);
    a_idx = (a_idx + 1) % a.length;
    var x = e.pageX,
        y = e.pageY;
    $i.css({
        "z-index": 144469,
        "top": y - 20,
        "left": x,
        "position": "absolute",
        "font-weight": "bold",
        color: c()
    });
    $("body").append($i);
    $i.animate({
            "top": y - 180,
            "opacity": 0
        },
        1500,
        function() {
            $i.remove()
        })
});