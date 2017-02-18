function makeExcelOkResult(headMsg, trMsg,color) {
	var result = '<table class="table table-condensed table-hover" style="width:100%;">'
			+ '<tbody>'
			+ '<tr>'
			+ '<td>'
			+ '<label class="control-label '
			+ color+'"><span class="msg-box n-right" style="" for="f_user_type">'
			+ '<span class="msg-wrap n-tip" role="alert" style="left: 0px; opacity: 1;"><span class="n-arrow"><b></b><i></i></span>'
			+ '<span class="glyphicon glyphicon-info-sign '
			+ color+'" style="top: -1px; font-size: 24px; vertical-align: middle;"></span>'
			+ '<span class="n-msg '
			+ color+'">'
			+ headMsg
			+ '</span>'
			+ '</span>'
			+ ' </span>' + '</label>' + '</td>' + '</tr>'
			+trMsg
			+'</tbody>' + ' </table>';
	return result;
}


//树下拉选择框：：选择事件
function S_NodeCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true);
    var ids = '', names = '';
    for (var i = 0; i < nodes.length; i++) {
        ids += ',' + nodes[i].id;
        names += ','+ nodes[i].name
	}
	if (ids.length > 0) {
		ids = ids.substr(1), names = names.substr(1)
	}

var $from = $('#' + treeId).data('fromObj');
    if ($from && $from.length) {
		$from.val(names)
	}
	$('#'+treeId+'_id').val(ids)

}
//树下拉选择框：：单击事件
function S_NodeClick(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    zTree.checkNode(treeNode, !treeNode.checked, true, true);
    event.preventDefault()
}

//查看teacher详情
function teacherView(id){
	$(this).dialog({id:'teacher-view-dialog', url:'/core/basic/teacher/base/teacherView/'+id+'?type=0', title:'教师详细信息',mask:'true',width:'1030',height:'660'});
}

