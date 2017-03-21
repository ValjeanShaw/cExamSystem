/**
 * Created by liulei on 2016/4/23.
 *
 *
 * ！！！！！！！！！！！！禁止修改此JS文件！！！！！！！！！！！！
 * ！！！！！！！！！！！！禁止修改此JS文件！！！！！！！！！！！！
 * ！！！！！！！！！！！！禁止修改此JS文件！！！！！！！！！！！！
 * ！！！！！！！！！！！！禁止修改此JS文件！！！！！！！！！！！！
 * ！！！！！！！！！！！！禁止修改此JS文件！！！！！！！！！！！！
 */
var postURL = "";//请求的URL地址
var clickStatus = "";//点击状态
var fileUpload="";//文件上传ID
//新建
function Add(){
    postURL = addUrl;
    clickStatus = "add";
    if(editorName!="null"){//清除KindEditor内容
        KindEditor.instances[0].html('');
    }
    $("#Form").form("clear");
    $("#dlg").dialog({title: "新建"});
    $('#dlg').dialog('open');
    $("#dlg").get(0).scrollTop=0;
}

//删除
function Delete(){
    clickStatus = "delete";
    postURL = deleteUrl;
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示', '确认删除此条数据吗?', function(result){
            if (result){
                //删除数据库记录
                var selectId = row[deleteId];
                var deleteJsonObject = eval("("+"{'"+deleteId+"':'"+selectId+"'}"+")");
                deleteJsonObject["moduleType"] = moduleType;
                $.post(postURL,deleteJsonObject,function(data){
                    if(data.result){
                        ShowMsg("删除成功");
                        $("#dg").datagrid("reload");//重新加载数据
                    }else {
                        ShowMsg("删除失败，请重新登录或联系管理员");
                    }
                });
            }
        });
    }else {
        ShowMsg("请选中一条数据");
    }
}
//修改
function Edit(){
    clickStatus = "edit";
    postURL = editUrl;
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $("#Form").form("clear");
        $('#Form').form('load', row);
        if(editorName!="null"){//为KindEditor赋值
            KindEditor.instances[0].html(row[editorName]);
        }
        $("#dlg").dialog({title: "修改"});
        $('#dlg').dialog('open');
        $("#dlg").get(0).scrollTop=0;
    }else {
        ShowMsg("请选中一条数据");
    }
}

//搜索
function doSearch(value,name){
    $('#dg').datagrid('reload',{
        sqlStr: value,
        moduleType:moduleType
    });
}
//保存
function Save(){
    if($("#Form").form('validate')){
        var jsonObject = $("#Form").serializeObject();
        jsonObject["moduleType"] = moduleType;
        if(editorName!=null&&editorName!="null"){
            editor.sync();
            jsonObject[editorName] = $("#"+editorName).val();
        }
        console.log(jsonObject);
        load();
        if(imageUpload==null||imageUpload==""){//如果没有图片上传
            if(!fileUpload){//如果没有文件上传
                UploadToDatabase(jsonObject);
            }else {//有文件上传
                if($("#"+fileUpload).val()!=null&&$("#"+fileUpload).val()!=""){
                    ajaxFileUpload("/FileUpload/No_Intercept_Upload.form",fileUpload,jsonObject,2);
                }else {
                    UploadToDatabase(jsonObject);
                }
            }
        }else {//有图片上传
            if($("#"+imageUpload).val()!=null&&$("#"+imageUpload).val()!=""){
                ajaxFileUpload("/ImageUpload/No_Intercept_Upload.form",imageUpload,jsonObject,1);
            }else {
                if(!fileUpload){//如果没有文件上传
                    UploadToDatabase(jsonObject);
                }else {//有文件上传
                    if($("#"+fileUpload).val()!=null&&$("#"+fileUpload).val()!=""){
                        ajaxFileUpload("/FileUpload/No_Intercept_Upload.form",fileUpload,jsonObject,2);
                    }else {
                        UploadToDatabase(jsonObject);
                    }
                }
            }
        }
    }else {
        ShowMsg("请按照要求填写");
    }
}
//序列化
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//弹出加载层
function load() {
    $("<div class=\"datagrid-mask\" style='z-index: 9999999999999999999;'></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\" style='z-index: 9999999999999999999;'></div>").html("正在加载，请稍候...").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
}

//取消加载层
function disLoad() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}

//文件(图片)上传
function ajaxFileUpload(url,id,jsonObject,time){
    $.ajaxFileUpload({
        url: url, //用于文件上传的服务器端请求地址
        secureuri: false, //一般设置为false
        fileElementId: id, //文件上传空间的id属性
        dataType: 'String', //返回值类型 一般设置为String
        success: function (data, status)  //服务器成功响应处理函数
        {
            if(data!=""||data!=null){
                var data = eval("(" + data + ")");
                if(typeof(data.error) != 'undefined') {
                    if (data.error == 0) {
                        jsonObject[id] = data.filename;
                        //如果time为1，证明图片已经上传完成，该上传文件，判断是否需要上传文件
                        if(time==1&&fileUpload!=""&&$("#"+fileUpload).val()!=null&&$("#"+fileUpload).val()!=""){
                            ajaxFileUpload("/FileUpload/No_Intercept_Upload.form",fileUpload,jsonObject,2);
                        }else {
                            UploadToDatabase(jsonObject);
                        }
                    } else {
                        ShowMsg("上传文件出错："+data.message);
                        disLoad();
                    }
                }else{
                    ShowMsg("上传文件出错："+data.message);
                    disLoad();
                }
            }else {
                ShowMsg("上传文件失败，请重新上传");
                disLoad();
            }
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
            ShowMsg("上传文件失败，请重新上传");
            disLoad();
        }
    });
}

//上传数据到数据库
function UploadToDatabase(jsonObject){
    $.post(postURL,jsonObject,function(data){
        disLoad();
        if(data.result){
            $('#dlg').dialog('close');
            ShowMsg("保存成功");
            $("#dg").datagrid("reload");
        }else {
            ShowMsg("保存中出现错误");
        }
    });
}
//查看新闻图片
function LookPicture(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $("#PictureViewer").attr("src","");
        //为弹出框添加蒙板层
        $('#dd').dialog({
            modal:true
        });
        var pictureName = row[imageUpload];
        $("#PictureViewer").attr("src","/Files/Images/"+pictureName);
    }else {
        ShowMsg("请选中一条数据");
    }

}