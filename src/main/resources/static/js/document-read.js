/**
 * 初始化目录
 * @param $url 目的的json体地址
 * @param $currentDocumentId 当前文档id
 */
function initDirectory($url, $currentDocumentId) {
    $.ajax({
        url: $url,
        type: "GET",
        beforeSend: function (xhr) {
            NProgress.start();
        },
        success: function (res) {
            if (res.errcode === 0) {
                var nodes = [];
                for (var i = 0, l = res.data.length; i < l; i++) {
                    //进行转换
                    var json = res.data[i];
                    var selected = $currentDocumentId == json.documentId;
                    var node = {
                        "id": json.documentId,
                        'parent': json.parentId === 0 ? '#' : json.parentId,
                        "text": json.documentName,
                        "identify": json.identify,
                        "version": json.version,
                        "state": {
                            "opened": json.isOpen,
                            "selected": selected
                        },
                        "a_attr": {"href": json.identify}
                    };
                    nodes.push(node)
                }
                _initTree(nodes)
            } else if (res.errcode === 6000) {
                window.location.href = "/";
            } else {
                layer.msg(res.message);
            }
        },
        complete: function () {
            NProgress.done();
        },
        error: function () {
            layer.msg("加载失败");
        }
    });


}

function _initTree($data) {
    $("#sidebar").jstree({
        'plugins': ["wholerow", "types"],
        "types": {
            "default": {
                "icon": false  // 删除默认图标
            }
        },
        'core': {
            'check_callback': true,
            "multiple": false,
            'animation': 0,
            "data": $data
        }
    }).on('select_node.jstree', function (node, selected, event) {
        $(".m-manual").removeClass('manual-mobile-show-left');
        loadDocument(selected);
    });
}

function loadDocument($selected) {
    console.log($selected)
}