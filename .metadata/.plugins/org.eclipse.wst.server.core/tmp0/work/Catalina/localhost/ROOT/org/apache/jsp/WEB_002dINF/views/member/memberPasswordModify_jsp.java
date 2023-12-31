/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.73
 * Generated at: 2023-11-08 07:13:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberPasswordModify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>회원 패스워드 변경</title>\n");
      out.write("<link rel=\"stylesheet\" href=\"/resources/css/board.css\">\n");
      out.write("<script src=\"/resources/js/getCookie.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("const passwordUpdate = async () =>  {\n");
      out.write("	\n");
      out.write("	const Pass = new_password.value;\n");
      out.write("	const Pass1 = new_password1.value;\n");
      out.write("	\n");
      out.write("	if(old_password.value == '') {\n");
      out.write("		alert('비밀번호를 입력하세요');\n");
      out.write("		old_password.focus();\n");
      out.write("		return false\n");
      out.write("	}\n");
      out.write("	if(Pass == '') {\n");
      out.write("		alert('암호를 입력하세요');\n");
      out.write("		new_password.focus();\n");
      out.write("		return false; \n");
      out.write("	}\n");
      out.write("	if(Pass1 == '') {\n");
      out.write("		alert('암호를 다시 한 번 입력하세요');\n");
      out.write("		new_password1.focus();\n");
      out.write("		return false; \n");
      out.write("	}\n");
      out.write("	if(Pass != Pass1) {\n");
      out.write("		alert('암호를 제대로 입력하세요');\n");
      out.write("		new_password1.focus();\n");
      out.write("		return false; \n");
      out.write("	}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" let num = Pass.search(/[0-9]/g); \n");
      out.write("			let eng = Pass.search(/[a-z]/ig); // i: 알파벳 대소문자 구분없이 검색\n");
      out.write("			let spe = Pass.search(/[`~!@#$%^&*|\\\\\\'\\\";:\\/?]/ig);        // 특수문자가 포함되어 있는지 검색\n");
      out.write("			if(Pass.length<8 || Pass.length > 20) {\n");
      out.write("				alert(\"암호는 8자리 ~ 20자 이내로 입력해 주세요.\");\n");
      out.write("				return false;\n");
      out.write("			} else if(Pass.search(/\\s/) != -1) {\n");
      out.write("				alert(\"암호는 공백 없이 입력해 주세요\");\n");
      out.write("				return false;\n");
      out.write("			}  else if(num<0 || eng <0 || spe <0) {\n");
      out.write("				alert(\"암호는 영문,숫자,특수문자를 혼합하여 입력해 주세요.\");\n");
      out.write("				return false;\n");
      out.write("			}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  let formData = new FormData();\n");
      out.write("  formData.append(\"old_password\",old_password.value);\n");
      out.write("  formData.append(\"new_password\",new_password.value);\n");
      out.write("    \n");
      out.write("  await fetch('/member/memberPasswordModify', {\n");
      out.write("  	method: 'POST',\n");
      out.write("      body: formData\n");
      out.write("  }).then((response)=> response.json())\n");
      out.write("  .then((data)=> {\n");
      out.write("  	   if(data.message === 'GOOD') {\n");
      out.write("  		   alert(\"패스워드가 변경되었습니다.\")\n");
      out.write("  		   logout();\n");
      out.write("  		 }else if(data.message === 'PASSWORD_NOT_FOUND') {\n");
      out.write("  			 msg.innerHTML = '잘못된 패스워드를 입력했습니다.'\n");
      out.write("  		 } else  {\n");
      out.write("  			alert(\"시스템 장애로 게시물 수정이 실패했습니다.\");\n");
      out.write("  		 }			 \n");
      out.write("  }).catch((error)=> {\n");
      out.write("  	\n");
      out.write("  console.log(\"error\" + error);	\n");
      out.write("  });\n");
      out.write("  \n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  const logout = () => {\n");
      out.write("	  let authkey = getCookie('authkey');\n");
      out.write("	  let userid = getCookie('userid');\n");
      out.write("	  let password = getCookie('password');\n");
      out.write("	  if(authkey != undefined) \n");
      out.write("		  document.cookie = 'authkey' + authkey + \";path=/;max-age=0\";\n");
      out.write("	  if(userid != undefined) \n");
      out.write("		  document.cookie = 'userid' + userid + \";path=/;max-age=0\";\n");
      out.write("	  if(password != undefined) \n");
      out.write("		  document.cookie = 'password' + password + \";path=/;max-age=0\";\n");
      out.write("		  \n");
      out.write("		  document.location.href='/member/memberSessionOut';\n");
      out.write("		  \n");
      out.write("		  \n");
      out.write("  }\n");
      out.write("  \n");
      out.write("  \n");
      out.write("  \n");
      out.write("\n");
      out.write("  </script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("<img src=\"/resources/images/logo.jpg\" id=\"topBanner\">\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<div class=\"main\">\n");
      out.write("<div class=\"ModifyForm\">\n");
      out.write("<h1>회원 패스워드 변경</h1>\n");
      out.write("<form name=\"modifyForm\" id=\"modifyForm\" method=\"POST\">\n");
      out.write("<input type=\"password\" id=\"old_password\" name=\"old_password\" class=\"input_field\" placeholder=\"기존 패스워드를 입력하세요\">\n");
      out.write("<p id=\"msg\" style=\"color:red; text-align:center;\"></p>\n");
      out.write("<input type=\"password\" id=\"new_password\" name=\"new_password\" class=\"input_field\" placeholder=\"신규 패스워드를 입력하세요\">\n");
      out.write("<p style=\"color:red;text-align:center;\"> 8~20이내의 영문자, 숫자, 특수문자 조합으로 암호를 만들어 주세요 </p>\n");
      out.write("<input type=\"password\" id=\"new_password1\" name=\"new_password1\" class=\"input_field\" placeholder=\"신규 패스워드를 한번 더 입력하세요\">\n");
      out.write("<input type=\"button\" class=\"btn_write\" value=\"패스워드 변경\" onclick=\"passwordUpdate()\">\n");
      out.write(" <input type=\"button\" class=\"btn_cancel\" value=\"취소\" onclick=\"history.back()\">\n");
      out.write(" \n");
      out.write("            \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</form>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
}
