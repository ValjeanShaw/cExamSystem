/**
 * Created by liulei on 2016/4/23.
 *
 *
 */
var postURL = "";//请求的URL地址
var oldPicture = "";

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
        if(editorName!=null&&editorName!=""){
            editor.sync();
            jsonObject[editorName] = $("#"+editorName).val();
        }
        load();
        if(imageUpload==null||imageUpload==""){
            UploadToDatabase(jsonObject);
        }else {
            if($("#picture").val()!=null&&$("#picture").val()!=""){
                ajaxFileUpload("FileUpload","picture",jsonObject);
            }else {
                UploadToDatabase(jsonObject);
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

//文件上传
function ajaxFileUpload(url,id,jsonObject){
    $.ajaxFileUpload({
        url: '/'+url+'/No_Intercept_Upload.form', //用于文件上传的服务器端请求地址
        secureuri: false, //一般设置为false
        fileElementId: id, //文件上传空间的id属性
        dataType: 'String', //返回值类型 一般设置为String
        success: function (data, status)  //服务器成功响应处理函数
        {
            if(data!=""||data!=null){
                var data = eval("(" + data + ")");
                if(typeof(data.error) != 'undefined') {
                    if (data.error == 0) {
                        jsonObject[imageUpload] = data.filename;
                        if(oldPicture!=""){
                            //删除新闻的文件
                            $.post("/FileUpload/DeleteFile.form",{fileName:oldPicture},function(data){
                                UploadToDatabase(jsonObject);
                            });
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
            ShowMsg("保存中出现错误，请检查用户名是否已经存在");
        }
    });
}