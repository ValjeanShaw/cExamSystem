/**
 * Created by liulei on 2016/5/15.
 */

var url =window.location.href;//动态获取网页url

//获取AccessToken，jsapi_ticket，签名，通过后台
$.post("/No_Intercept/WeChat/getAccessToken.form",{url:url},function(data){
    if(data.error==null){
        //通过config接口注入权限验证配置
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx86d75965e3b084b8', // 必填，公众号的唯一标识-真正公众号
            timestamp: data.timestamp, // 必填，生成签名的时间戳
            nonceStr: data.nonceStr, // 必填，生成签名的随机串
            signature: data.signature,// 必填，签名，见附录1
            jsApiList: [
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'chooseImage',
                'uploadImage'
            ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    }else {
        layer.msg(data.error);
        return;
    }
});

//通过ready接口处理成功验证
wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    //分享到朋友圈
    //wx.onMenuShareTimeline({
    //    title: '必应', // 分享标题
    //    link: 'http://cn.bing.com/', // 分享链接
    //    imgUrl: headimgurl, // 分享图标
    //    success: function () {
    //        // 用户确认分享后执行的回调函数
    //        layer.msg("分享成功");
    //    },
    //    cancel: function () {
    //        // 用户取消分享后执行的回调函数
    //        layer.msg("您取消了分享");
    //    }
    //});
    //分享给好友
    //wx.onMenuShareAppMessage({
    //    title: '必应', // 分享标题
    //    desc: '搜索引擎', // 分享描述
    //    link: 'http://cn.bing.com/', // 分享链接
    //    imgUrl: headimgurl, // 分享图标
    //    type: '', // 分享类型,music、video或link，不填默认为link
    //    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    //    success: function () {
    //        // 用户确认分享后执行的回调函数
    //        layer.msg("发送成功");
    //    },
    //    cancel: function () {
    //        // 用户取消分享后执行的回调函数
    //        layer.msg("您取消了发送");
    //    }
    //});
    ////选择图片接口
    //wx.chooseImage({
    //    count: 1, // 默认9
    //    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
    //    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
    //    success: function (res) {
    //        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
    //        $("#HeadImg").attr("src",localIds);
    //    }
    //});
    //微信支付
    //wx.chooseWXPay({
    //    timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
    //    nonceStr: '', // 支付签名随机串，不长于 32 位
    //    package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
    //    signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
    //    paySign: '', // 支付签名
    //    success: function (res) {
    //        // 支付成功后的回调函数
    //    }
    //});
});

//通过error接口处理失败验证
wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});

//判断当前客户端版本是否支持指定JS接口
wx.checkJsApi({
    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
    success: function(res) {
        // 以键值对的形式返回，可用的api值true，不可用为false
        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
    }
});

//选择上传图片函数
function SelectImage(){

    //选择图片接口
    wx.chooseImage({
        count: 1, // 默认9
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            //上传图片接口
            wx.uploadImage({
                localId: localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    var serverId = res.serverId; // 返回图片的服务器端ID
                    $.ajax({
                        url:"/No_Intercept/WeChat/downImage.form",
                        type:"post",
                        datatype:"json",
                        data:{mediaId:serverId},
                        success:function(data){

                        },
                        error:function(){
                            layer.msg("该图片上传失败，请重新上传")
                        }
                    })
                },
                error:function(){
                    layer.msg("该图片上传失败，请重新上传");
                }
            });
        },
        error:function(){
            layer.msg("该图片上传失败，请重新上传")
        }
    });
}