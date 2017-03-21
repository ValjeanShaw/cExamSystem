// JScript 文件

String.prototype.Trim = function()
{
    return this.replace(/^\s+/g, "").replace(/\s+$/g, "");
}

function JSCookie()
{
    this.GetCookie = function(key)
    {
        var cookie = document.cookie;
        var cookieArray = cookie.split(';');
        var getvalue = "";
        for (var i = 0; i < cookieArray.length; i++)
        {
            if (cookieArray[i].Trim().substr(0, key.length) == key)
            {
                getvalue = cookieArray[i].Trim().substr(key.length + 1);
                break;
            }
        }
        return getvalue;
    };

    this.GetChild = function(cookiekey, childkey)
    {
        var child = this.GetCookie(cookiekey);
        var childs = child.split('&');
        var getvalue = "";
        for (var i = 0; i < childs.length; i++)
        {
            if (childs[i].Trim().substr(0, childkey.length) == childkey)
            {
                getvalue = childs[i].Trim().substr(childkey.length + 1);
                break;
            }
        }
        return getvalue;
    };

    this.SetCookie = function(key, value, expire, domain, path)
    {
        //将一些特殊符号使用十六进制表示，例如空格将会编码为“20%”。
        //从而可以存储于cookie值中，而且使用此种方案还可以避免中文乱码的出现。
        //当使用escape()编码后，在取出值以后需要使用unescape()进行解码才能得到原来的cookie值。

        var cookie = "";
        if (key != null && value != null)
        {
            cookie += key + "=" + value + ";";
        }
        if (expire != null)
        {
            cookie += "expires=" + expire.toGMTString() + ";";
        }
        if (domain != null)
        {
            cookie += "domain=" + domain + ";";
        }
        if (path != null)
        {
            cookie += "path=" + path + ";";
        }
        document.cookie = cookie;
    };

    this.Expire = function(key)
    {
        expire_time = new Date();
        expire_time.setFullYear(expire_time.getFullYear() - 1);
        var cookie = " " + key + "=e;expires=" + expire_time + ";"
        document.cookie = cookie;
    }
}

/************************************************************************
一、设置cookie 
代码如下:
var cookie = new JSCookie(); 
// 普通设置 
cookie.SetCookie("key1","val1"); 
// 过期时间为一年 
var expire_time = new Date(); 
expire_time.setFullYear(expire_time.getFullYear() + 1); 
cookie.SetCookie("key2","val2",expire_time); 
// 设置域及路径，带过期时间 
cookie.SetCookie("key3","val3",expire_time,".cnblogs.com","/"); 
// 设置带子键的cookie,子键分别是k1,k2,k3 
cookie.SetCookie("key4","k1=1&k2=2&k3=3"); 


二、读取cookie 
代码如下:
// 简单获取 
cookie.GetCookie("key1"); 
cookie.GetCookie("key2"); 
cookie.GetCookie("key3"); 
cookie.GetCookie("key4"); 
// 获取key4的子键k1值 
cookie.GetChild("key4","k1"); 


三、删除 
代码如下:
cookie.Expire("key1"); 
cookie.Expire("key2"); 
cookie.Expire("key3"); 
cookie.Expire("key4"); 
****************************************************************************/