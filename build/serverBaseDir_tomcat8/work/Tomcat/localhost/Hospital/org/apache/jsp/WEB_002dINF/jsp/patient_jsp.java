/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.33
 * Generated at: 2017-05-04 13:46:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class patient_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("jar:file:/C:/Users/Pasha/.gradle/caches/modules-2/files-2.1/jstl/jstl/1.2/74aca283cd4f4b4f3e425f5820cda58f44409547/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153374282000L));
    _jspx_dependants.put("jar:file:/C:/Users/Pasha/.gradle/caches/modules-2/files-2.1/jstl/jstl/1.2/74aca283cd4f4b4f3e425f5820cda58f44409547/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153374282000L));
    _jspx_dependants.put("file:/C:/Users/Pasha/.gradle/caches/modules-2/files-2.1/jstl/jstl/1.2/74aca283cd4f4b4f3e425f5820cda58f44409547/jstl-1.2.jar", Long.valueOf(1487059690083L));
    _jspx_dependants.put("/WEB-INF/tags/html.tag", Long.valueOf(1493905542195L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_t_005fhtml_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_t_005fhtml_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  t:html
    org.apache.jsp.tag.web.html_tag _jspx_th_t_005fhtml_005f0 = (new org.apache.jsp.tag.web.html_tag());
    _jsp_getInstanceManager().newInstance(_jspx_th_t_005fhtml_005f0);
    _jspx_th_t_005fhtml_005f0.setJspContext(_jspx_page_context);
    _jspx_th_t_005fhtml_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_t_005fhtml_005f0, null));
    _jspx_th_t_005fhtml_005f0.doTag();
    _jsp_getInstanceManager().destroyInstance(_jspx_th_t_005fhtml_005f0);
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, javax.servlet.jsp.JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write("\r\n");
      out.write("     <div class=\"easyui-panel\" title=\"Новый пациент\" style=\"width:100%;max-width:400px;padding:30px 60px;\">\r\n");
      out.write("    <form id=\"ff\" method=\"post\">\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <input class=\"easyui-textbox\" name=\"firstname\" style=\"width:100%\" data-options=\"label:'Фамилия:',required:true\">\r\n");
      out.write("      </div>\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <input class=\"easyui-textbox\" name=\"lastname\" style=\"width:100%\" data-options=\"label:'Имя:',required:true\">\r\n");
      out.write("      </div>\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <input class=\"easyui-textbox\" name=\"middlename\" style=\"width:100%\" data-options=\"label:'Отчество:',required:true\">\r\n");
      out.write("      </div>\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <input class=\"easyui-datebox\" name=\"birthday\" data-options=\"label:'Дата рождения'\" style=\"width:100%;\">\r\n");
      out.write("      </div>\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <input class=\"easyui-textbox\" name=\"address\" style=\"width:100%;height:60px\" data-options=\"label:'Адресс:',multiline:true\">\r\n");
      out.write("      </div>\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <select class=\"easyui-combobox\" name=\"gender\" label=\"Пол\" style=\"width:100%\">\r\n");
      out.write("      <option value=\"MALE\">мужчина</option>\r\n");
      out.write("      <option value=\"FEMALE\">женщина</option>\r\n");
      out.write("      </select>\r\n");
      out.write("      </div>\r\n");
      out.write("      <div style=\"margin-bottom:20px\">\r\n");
      out.write("      <input class=\"easyui-textbox\" name=\"passport\" style=\"width:100%\" data-options=\"label:'Паспорт:',required:true\">\r\n");
      out.write("      </div>\r\n");
      out.write("       <div style=\"text-align:center;padding:5px 0\">\r\n");
      out.write("            <a href=\"javascript:void(0)\" data-options=\"iconCls:'icon-ok'\" class=\"easyui-linkbutton\" onclick=\" \" style=\"width:80px\">ОК</a>\r\n");
      out.write("            <a href=\"javascript:void(0)\" data-options=\"iconCls:'icon-cancel'\" class=\"easyui-linkbutton\" onclick=\" \" style=\"width:110px\">Отменить</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws javax.servlet.jsp.JspException
    {
      javax.servlet.jsp.JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        Object _jspx_saved_JspContext = this.jspContext.getELContext().getContext(javax.servlet.jsp.JspContext.class);
        this.jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
        }
        jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,_jspx_saved_JspContext);
      }
      catch( java.lang.Throwable e ) {
        if (e instanceof javax.servlet.jsp.SkipPageException)
            throw (javax.servlet.jsp.SkipPageException) e;
        throw new javax.servlet.jsp.JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}