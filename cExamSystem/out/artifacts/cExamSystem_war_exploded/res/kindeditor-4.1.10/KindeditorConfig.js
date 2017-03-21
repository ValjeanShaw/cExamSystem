/**
 * Created by liulei on 2016/5/9.
 */
//KindEditor基本配置
var editor;
KindEditor.ready(function(K) {
    editor = K.create('textarea[name="'+editorName+'"]', {
        cssPath : '../../../asset/kindeditor-4.1.10/plugins/code/prettify.css',
        uploadJson : '/No_Intercept/KindEditor/FileUpload.form',
        fileManagerJson : '/No_Intercept/KindEditor/FileManager.form',
        allowFileManager : true
    });
    prettyPrint();
});