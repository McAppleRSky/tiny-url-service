<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <title>Tiny url client</title>
  <script>
    function selectContent(src) {
      let selectedAll;
      let selectedAny;
      switch (src.value) {
        case 'login':
          selectedAll = document.querySelectorAll(".content_selecting");
          selectedAny = document.querySelector('#content_login');
          selectedAll.forEach((element) => {
            element.classList.remove("visible");
            element.classList.add("hidden")
          });
          selectedAny.classList.remove("hidden");
          selectedAny.classList.remove("visible");
          break;
        case 'token':
          selectedAll = document.querySelectorAll(".content_selecting");
          selectedAny = document.querySelector('#content_token');
          selectedAll.forEach((element) => {
            element.classList.remove("visible");
            element.classList.add("hidden")
          });
          selectedAny.classList.remove("hidden");
          selectedAny.classList.remove("visible");
          break;
        case 'link':
          selectedAll = document.querySelectorAll(".content_selecting");
          selectedAny = document.querySelector('#content_link');
          selectedAll.forEach((element) => {
            element.classList.remove("visible");
            element.classList.add("hidden")
          });
          selectedAny.classList.remove("hidden");
          selectedAny.classList.remove("visible");
          break;
        case 'oper':
          selectedAll = document.querySelectorAll(".content_selecting");
          selectedAny = document.querySelector('#content_oper');
          selectedAll.forEach((element) => {
            element.classList.remove("visible");
            element.classList.add("hidden")
          });
          selectedAny.classList.remove("hidden");
          selectedAny.classList.remove("visible");
          break;
        default:
          selectedAll = document.querySelectorAll(".content_selecting");
          selectedAny = document.querySelector('#content_greeting');
          selectedAll.forEach((element) => {
            element.classList.remove("visible");
            element.classList.add("hidden")
          });
          selectedAny.classList.remove("hidden");
          selectedAny.classList.remove("visible");
          break;
      }
    }
    // const loginForm = document.getElementById("myForm");
    const loginXHR = new XMLHttpRequest();
    const tokenXHR= new XMLHttpRequest();
    var loginForm;
    var tokens;
    var sendToken;

    function handleEvent(e) {
      log.textContent = log.textContent + " (" + e.type + ") (" + e.loaded + ") bytes transferred"
    }
    loginXHR.addEventListener('loadstart', handleEvent);
    loginXHR.addEventListener('loadend', handleEvent);
    loginXHR.addEventListener('progress', handleEvent);
    loginXHR.addEventListener("error", (event) => {
      handleEvent(event);
      alert('Oops! Login went wrong.')
    });
    loginXHR.addEventListener('error', handleEvent);
    // loginXHR.addEventListener('abort', handleEvent);
    loginXHR.addEventListener("load", (event) => {
        handleEvent(event);
        document.getElementById('login_status').textContent = loginXHR.status + " " + loginXHR.statusText;
        tokens = JSON.parse(event.target.responseText);
        document.getElementById('access_token').textContent = tokens.accessToken;
        document.getElementById('refresh_token').textContent = tokens.refreshToken;
    });
    tokenXHR.addEventListener("load", (event) => {
        handleEvent(event);
        document.getElementById('token_status').textContent = tokenXHR.status + " " + tokenXHR.statusText;
        let token = JSON.parse(event.target.responseText);
        tokens.accessToken = token.accessToken;
        document.getElementById('access_token').textContent = token.accessToken;
        // document.getElementById('refresh_token').textContent = tokens.refreshToken;
    });

    function sendLogin() {
      let formData = new FormData(loginForm);
      loginForm.reset();
      if (formData.get("login") == "" || formData.get("password") == "") {
        return;
      }
      loginXHR.open("POST", "/api/0.0.1/login");
      // let formData = new FormData(loginForm);
      loginXHR.send(formData);
    };
    function getAccess() {
      if (tokens.refreshToken=="") {
        return;
      }
      tokenXHR.open("POST", "/api/0.0.1/token");
      let token = {refreshToken:tokens.refreshToken};
      tokenXHR.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      tokenXHR.send(JSON.stringify(token));
    };
    document.addEventListener("DOMContentLoaded", () => {
      loginForm = document.forms.login_form;
      sendToken = document.getElementById('send_token');
      log = document.querySelector('.event-log');
      loginForm.addEventListener("submit", (event) => {
        event.preventDefault();
        sendLogin();
      });
      sendToken.addEventListener('click', () =>{
        getAccess();
      });
    })
  </script>
  <style type="text/css" media="screen">
    header {
      text-align: center;
      font-family: serif;
      font-weight: normal;
      border-bottom: 1px solid #57b1dc;
    }

    aside {
      float: right;
      width: 19%;
      border-left: 1px solid #57b1dc;
      padding-left: 15px;
    }

    main {
      width: 80%;
      min-height: 220px;
    }

    footer {
      border-top: 1px solid #57b1dc;
    }

    .hidden {
      display: none;
    }

    .visible {
      display: block;
    }
  </style>
</head>

<body>
  <header>
    <h2>Manage Tiny url:</h2>
  </header>
  <aside>
    <h2>Phase:</h2>
    <section>
      <form name="content_selector_form">
        <p>
          <input name="content_selector" type="radio" value="login" id="select_login" onchange="selectContent(this)">
          <label for="select_login">Login</label>
        </p>
        <p>
          <input name="content_selector" type="radio" value="token" id="select_token" onchange="selectContent(this)">
          <label for="select_token">Token</label>
        </p>
        <p>
          <input name="content_selector" type="radio" value="link" id="select_link" onchange="selectContent(this)">
          <label for="select_link">Link</label>
        </p>
        <p>
          <input name="content_selector" type="radio" value="oper" id="select_radio" onchange="selectContent(this)">
          <label for="select_radio">Operator</label>
        </p>
      </form>
    </section>
    <section>
    </section>
  </aside>
  <main>
    <article>
      <div id="content_greeting" class="content_selecting visible">
        <h1>Greeting !</h1>
      </div>
      <div id="content_login" class="content_selecting hidden">
        <h1>Enter login</h1>
        <form name="login_form">
          <table>
            <colgroup>
              <col>
            </colgroup>
            <tbody>
              <tr>
                <td>
                  <label for="login">Operator login:</label>
                </td>
                <td>
                  <input type="text" id="login" name="login">
                </td>
              </tr>
              <tr>
                <td>
                  <label for="password">Operator password:</label>
                </td>
                <td>
                  <input type="text" id="password" name="password">
                </td>
              </tr>
              <tr>
                <td>
                  <input type="submit" value="Enter">
                </td>
                <td>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
        <p>Login status :
          <label id="login_status">-</label>
        </p>
      </div>
      <div id="content_token" class="content_selecting hidden">
        <h1>Get token</h1>
        <label for="sendToken">Get access token:</label>
        <input type="button" id="send_token" value="send token">
      </div>
      <div id="content_link" class="content_selecting hidden">
        <h1>Content links</h1>
        <div id="container">
          <div style="visibility: visible; overflow: visible; position: absolute; left: 100px; top: 100px;">
            <div class="mainSection">
              <label onclick="createForm()" style="outline: 2px solid #000;">(+) Add docs on this list</label>
            </div>
            <div class="mainSection">
              <div class="">
              </div>
              <table id="mainTable" style="text-align: center;">
                <thead>
                  <tr>
                    <th>
                    </th>
                    <th>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                    </td>
                    <td nowrap>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td nowrap>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td nowrap>
                    </td>
                    <td>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="mainSection">
            </div>
          </div>
        </div>
      </div>
      <div id="content_oper" class="content_selecting hidden">
        <h1>Content operator</h1>
      </div>
    </article>
    <article>
      <h2>Access token</h2>
      <label id="access_token">-</label>
      <h2>Refresh token</h2>
      <label id="refresh_token">-</label>
      <p>token status :
        <label id="token_status">-</label>
      </p>
    </article>
  </main>
  <footer>
    <h2>log area :</h2>
    <textarea readonly class="event-log"></textarea>
  </footer>
</body>

</html>
