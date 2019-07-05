$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'biz/task/my',
        datatype: "json",
        colModel: [			
			{ label: '任务ID', name: 'taskId', index: "task_id", width: 45, key: true },
			{ label: '任务名', name: 'taskName', width: 75 },
            { label: '所属项目', name: 'projectName', sortable: false, width: 75 },
			{ label: '优先级', name: 'priority', width: 90 },
			// { label: '指派给', name: 'username', width: 90 },
			{ label: '开始时间', name: 'startTime', width: 100 },
			{ label: '结束时间', name: 'endTime', width: 100 },
			{ label: '任务类型', name: 'taskType', width: 60, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">问题</span>' :
					'<span class="label label-success">任务</span>';
			}},
            { label: '任务状态', name: 'status', width: 100, formatter: function (value, options, row) {
                    if (value === 0) return '<span class="label label-danger">未开始</span>';
                    if (value === 1)  return '<span class="label label-info">进行中</span>';
                    if (value === 2)  return '<span class="label label-success">完结</span>';
                }},
            { label: '完成比例%', name: 'percent', width: 100 }
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


    //日期时间选择器
    laydate.render({
        elem: '#startTime'
        , type: 'datetime',
        done: function(value, date, endDate){
            vm.task.startTime=value;

        }
    });

    laydate.render({
        elem: '#endTime'
        , type: 'datetime',
        done: function(value, date, endDate){
            vm.task.endTime=value;

        }
    });

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            taskName: null
        },
        showList: true,
        title:null,
        projectList: null,
        userList: null,
        task:{
            taskName: null,
            projectId:null,
            userId:null,
            taskType: null,
            priority: null,
            percent: null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            //获取项目列表
            vm.getProjectList();
            //获取用户列表
            vm.getUserList();

        },

        getProjectList: function(){
            $.get(baseURL + "biz/project/all", function(r){
                vm.projectList = r;
            })
        },

        getUserList: function(){
            $.get(baseURL + "sys/user/all", function(r){
                vm.userList = r;
            })
        },


        update: function () {
            var taskId = getSelectedRow();
            if(taskId == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";
            //获取项目列表
            vm.getProjectList();
            //获取用户列表
            vm.getUserList();

            vm.getTaskInfo(taskId);
        },

        getTaskInfo: function(taskId){
            $.get(baseURL + "biz/task/info/"+taskId, function(r){
                vm.task = r.task;
            });
        },

        del: function () {
            var taskIds = getSelectedRows();
            if(taskIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "biz/task/delete",
                    contentType: "application/json",
                    data: JSON.stringify(taskIds),
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
            });
        },
        saveOrUpdate: function () {
            var url = vm.task.taskId == null ? "biz/task/save" : "biz/task/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.task),
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

        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'taskName': vm.q.taskName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});