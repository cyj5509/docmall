<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSTL Core 라이브러리 -->
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>AdminLTE 2 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta
      content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
      name="viewport"
    />
    <%@ include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
  </head>
  <!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->

  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      <!-- Main Header -->
      <%@ include file="/WEB-INF/views/admin/include/header.jsp" %>

      <!-- Left side column. contains the logo and sidebar -->
      <%@ include file="/WEB-INF/views/admin/include/nav.jsp" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Page Header
            <small>Optional description</small>
          </h1>
          <ol class="breadcrumb">
            <li>
              <a href="#"><i class="fa fa-dashboard"></i> Level</a>
            </li>
            <li class="active">Here</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">
          <div class="row"> <!-- 합이 12까지 사용 가능. 반드시 고정될 필요는 없음 -->
            <!-- <div class="col-해상도-숫자"></div>  -->
            <div class="col-md-12">
              <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title">Product List</h3>
                </div>
  
                <div class="box-body">
                  <table class="table table-bordered">
                    <tbody>
                      <tr>
                        <th style="width: 2%"><input type="checkbox" id="checkbox"></th>
                        <th style="width: 8%">상품번호</th>
                        <th style="width: 25%">상품명</th>
                        <th style="width: 10%">가격</th>
                        <th style="width: 20%">등록일</th>
                        <th style="width: 15%">판매여부</th>
                        <th style="width: 10%">수정</th>
                        <th style="width: 10%">삭제</th>
                      </tr>
                      <!-- BoardController에서 작성한부이름과 동일한 이름을 items로 작성 -->
                      <!-- 목록이 출력되는 부분 -->
                      <c:forEach items="${pro_list}" var="productVO">
                        <tr>
                          <td><input type="checkbox" id="checkbox"></td>
                          <td>${productVO.pro_num}</td>
                          <td>                        
                            <a class="move" href="#" data-bno="${productVO.pro_num }"><img src="/admin/product/imageDisplay?dateFolderName=${productVO.pro_up_folder }&fileName=s_${productVO.pro_img }"></a>
                            <a class="move" href="#" data-bno="${productVO.pro_num}">${productVO.pro_name}</a>
                          </td> <!-- 클래스명 move는 제목과 관련 -->
                          <td> ${productVO.pro_price}</td>
                          <td>
                            <fmt:formatDate value="${productVO.pro_date}" pattern="yyyy-MM-dd" />
                          </td>
                          <td>${productVO.pro_buy}</td>
                          <td><button type="button" class="btn btn-primary">수정</button></td>
                          <td><button type="button" class="btn btn-danger">삭제</buttonn></td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
  
                <div class="box-footer clearfix">
                  <div class="row">
                    <div class="col-6">
                      <nav aria-label="...">
                        <ul class="pagination">
                          <!-- 이전 표시 여부 -->
                          <c:if test="${pageMaker.prev}">
                            <li class="page-item">
                              <a href="/board/list?pageNum=${pageMaker.startPage - 1}" class="page-link movepage">Previous</a>
                            </li>
                          </c:if>
  
                          <!-- 페이지 번호 출력 작업 -->
                          <!--  1 2 3 4 5 6 7 8 9 10 [다음] -->
                          <!--  [이전] 11 12 13 14 15 16 17 18 19 20 [다음] -->
                          <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
                            <li class='page-item ${pageMaker.cri.pageNum == num ? "active" : "" }' aria-current="page">
                              <a class="page-link movepage" href="#" data-page="${num}">${num}</a> <!-- 임의로 만든 클래스명 movepage는 페이지 번호와 관련 -->
                            </li>
                          </c:forEach>
  
                          <!-- 다음 표시 여부 -->
                          <c:if test="${pageMaker.next}">
                            <li class="page-item">
                              <a href="/board/list?pageNum=${pageMaker.endPage + 1}" class="page-link movepage">Next</a>
                            </li>
                          </c:if>
                        </ul>
                      </nav>
                    </div>
                    <div class="col-6">
                      <form action="/admin/product/pro_list" method="get">
                        <select name="type">
                          <option selected>검색 종류 선택</option>
                          <option value="N" ${pageMaker.cri.type == 'N' ? 'selected' : ''}>상품명</option>
                          <option value="C" ${pageMaker.cri.type == 'C' ? 'selected' : ''}>상품코드</option>
                          <option value="P" ${pageMaker.cri.type == 'P' ? 'selected' : ''}>제작자</option>
                          <option value="NC" ${pageMaker.cri.type == 'NC' ? 'selected' : ''}>상품명 or 상품코드</option>
                          <option value="NW" ${pageMaker.cri.type == 'NW' ? 'selected' : ''}>상품명 or 제작자</option>
                          <option value="NWC" ${pageMaker.cri.type == 'NWC' ? 'selected' : ''}>상품명 or 상품코드 or 제작자</option>
                        </select>
                        <input type="text" name="keyword" value="${pageMaker.cri.keyword}" />
                        <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
                        <input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
                        <button type="submit" class="btn btn-primary">검색</button>
                      </form>
  
                      <!-- <form id="actionForm">의 용도 -->
                      <!-- 1) 페이지 번호([이전] 1 2 3 4 5 ... [다음])를 클릭할 때 사용: action="/board/get" 
                      <!-- 2) 목록에서 상품 이미지 또는 상품명을 클릭할 때 사용: action="/board/get" -->
                      <form id="actionForm" action="" method="get">
                        <input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
                        <input type="hidden" name="amount" id="amount" value="${pageMaker.cri.amount}" />
                        <input type="hidden" name="type" id="type" value="${pageMaker.cri.type}" />
                        <input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />
                        <input type="hidden" name="pro_num" id="pro_num" />
                      </form>
                    </div>
                  </div>
                  <a class="btn btn-primary" href="/board/register" role="button">글쓰기</a>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!--------------------------
        | Your Page Content Here |
        -------------------------->

        <!-- /.content -->
      </div>
      <!-- /.content-wrapper -->

      <!-- Main Footer -->
      <%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>

      <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li class="active">
            <a href="#control-sidebar-home-tab" data-toggle="tab"
              ><i class="fa fa-home"></i
            ></a>
          </li>
          <li>
            <a href="#control-sidebar-settings-tab" data-toggle="tab"
              ><i class="fa fa-gears"></i
            ></a>
          </li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane active" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">Recent Activity</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript:;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">
                      Langdon's Birthday
                    </h4>

                    <p>Will be 23 on April 24th</p>
                  </div>
                </a>
              </li>
            </ul>
            <!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">Tasks Progress</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript:;">
                  <h4 class="control-sidebar-subheading">
                    Custom Template Design
                    <span class="pull-right-container">
                      <span class="label label-danger pull-right">70%</span>
                    </span>
                  </h4>

                  <div class="progress progress-xxs">
                    <div
                      class="progress-bar progress-bar-danger"
                      style="width: 70%"
                    ></div>
                  </div>
                </a>
              </li>
            </ul>
            <!-- /.control-sidebar-menu -->
          </div>
          <!-- /.tab-pane -->
          <!-- Stats tab content -->
          <div class="tab-pane" id="control-sidebar-stats-tab">
            Stats Tab Content
          </div>
          <!-- /.tab-pane -->
          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Report panel usage
                  <input type="checkbox" class="pull-right" checked />
                </label>

                <p>Some information about this general settings option</p>
              </div>
              <!-- /.form-group -->
            </form>
          </div>
          <!-- /.tab-pane -->
        </div>
      </aside>
      <!-- /.control-sidebar -->
      <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
    </div>
    <!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->
    <%@ include file="/WEB-INF/views/admin/include/plugin2.jsp" %>
    <script
      src="/bower_components/ckeditor/ckeditor.js"
      type="text/javascript"
    ></script>

    <script>
      $(document).ready(function () {
        
        let actionForm = $("#actionForm");

        // [이전] 1 2 3 4 5 ... [다음] 클릭 이벤트 설정. <a> 태그
        $(".movepage").on("click", function(e) {
          e.preventDefault(); // a 태그의 링크 기능을 제거

          actionForm.attr("action", "/admin/product/pro_list");
        });        
      });
    </script>
  </body>
</html>
