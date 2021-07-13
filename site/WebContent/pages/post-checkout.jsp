<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Pagamento Completato</title>
  </head>
  <style>
    .main {
      text-align: center;
    }
  </style>
  <body>
    <%@ include file="../components/navbar.jsp" %>
    <div class="main">
      <h1 class="feedback-title">Stiamo processando il pagamento</h1>
      <span id="icon">
        <svg
          id="check"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          viewBox="0 0 100 100"
          xml:space="preserve"
          style="width: 70px; height: 70px"
          class="progress"
        >
          <circle id="circle" cx="50" cy="50" r="46" fill="transparent" />
          <polyline id="tick" points="25,55 45,70 75,33" fill="transparent" />
        </svg>
      </span>
    </div>
    <%@ include file="../components/footer.jsp" %>
    <script>
      const circle = document.querySelector("#check");
      setTimeout(() => {
        circle.classList.toggle("progress");
        circle.classList.toggle("ready");
      }, 3000);
    </script>
  </body>
</html>
