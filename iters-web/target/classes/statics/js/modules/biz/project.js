$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'biz/project/list',
        datatype: "json",
        colModel: [			
			{ label: '项目ID', name: 'projectId', index: "project_id", width: 45, key: true },
			{ label: '项目名', name: 'projectName', width: 75 },
            { label: '所属部门', name: 'deptName', sortable: false, width: 75 },
			{ label: '项目说明', name: 'describe', width: 90 },
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'createTime', index: "create_time", width: 85}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            projectName: null
        },
        showList: true,
        title:null,
        project:{
            deptId:null,
            deptName:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.getDept();
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.project.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.project.deptName = node.name;
                }
            })
        },
        update: function () {
            var projectId = getSelectedRow();
            if(projectId == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getProjectInfo(projectId);

        },

        getProjectInfo: function(projectId){
            $.get(baseURL + "biz/project/info/"+projectId, function(r){
                vm.project = r.project;
                vm.getDept();
            });
        },
        del: function () {
            var projectIds = getSelectedRows();
            if(projectIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "biz/project/delete",
                    contentType: "application/json",
                    data: JSON.stringify(projectIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.project.projectId == null ? "biz/project/save" : "biz/project/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.project),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },

        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.project.deptId = node[0].deptId;
                    vm.project.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'projectName': vm.q.projectName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});