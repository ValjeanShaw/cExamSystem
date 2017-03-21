function ShowMsg(message, position)
{
    //$.messager.show({
    //    title:'提示',
    //    msg:message,
    //    showType:'fade',
    //    style:{
    //        right:'',
    //        bottom:''
    //    }
    //});
    switch (position)
    {
        case null:
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide'
            });
            break;
        case "TL":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    right: '',
                    left: 0,
                    top: document.body.scrollTop + document.documentElement.scrollTop,
                    bottom: ''
                }
            });
            break;
        case "TC":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    right: '',
                    top: document.body.scrollTop + document.documentElement.scrollTop,
                    bottom: ''
                }
            });
            break;
        case "TR":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    left: '',
                    right: 0,
                    top: document.body.scrollTop + document.documentElement.scrollTop,
                    bottom: ''
                }
            });
            break;
        case "ML":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    left: 0,
                    right: '',
                    bottom: ''
                }
            });
            break;
        case "MC":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'show',
                style: {
                    right: '',
                    bottom: ''
                }
            });
            break;
        case "MR":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    left: '',
                    right: 0,
                    bottom: ''
                }
            });
            break;
        case "BL":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    left: 0,
                    right: '',
                    top: '',
                    bottom: -document.body.scrollTop - document.documentElement.scrollTop
                }
            });
            break;
        case "BC":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide',
                style: {
                    title: 'My Title',
                    msg: 'The message content',
                    showType: 'slide',
                    style: {
                        right: '',
                        top: '',
                        bottom: -document.body.scrollTop - document.documentElement.scrollTop
                    }
                }
            });
            break;
        case "BR":
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide'
            });
            break;
        default:
            $.messager.show({
                title: '温馨提示',
                msg: '' + message + '',
                showType: 'slide'
            });
    }
}

function AlertMsg(message, type)
{
    switch (type)
    {
        case null:
            $.messager.alert('提示', message);
            break;
        case "E":
            $.messager.alert('温馨提示', message, 'error');
            break;
        case "I":
            $.messager.alert('温馨提示', message, 'info');
            break;
        case "Q":
            $.messager.alert('温馨提示', message, 'question');
            break;
        case "W":
            $.messager.alert('温馨提示', message, 'warning');
            break;
        default:
            $.messager.alert('温馨提示', message);
    }

}