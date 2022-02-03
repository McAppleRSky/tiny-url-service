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
        case 'refresh':
          selectedAll = document.querySelectorAll(".content_selecting");
          selectedAny = document.querySelector('#content_refresh');
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
    const xhr = {
      login: new XMLHttpRequest(),
      token: new XMLHttpRequest(),
      refresh:new XMLHttpRequest(),
      oper: new XMLHttpRequest()
    };
    var token, tokens;
    var sendToken;
    var oper;
// var opersResponse;
    function handleEvent(e) {
      log.textContent = log.textContent + " (" + e.type + ") (" + e.loaded + ") bytes transferred"
    }
    xhr.login.addEventListener('loadstart', handleEvent);
    xhr.login.addEventListener('loadend', handleEvent);
    xhr.login.addEventListener('progress', handleEvent);
    xhr.login.addEventListener("error", (event) => {
      handleEvent(event);
      alert('Oops! Login went wrong.')
    });
    xhr.login.addEventListener('error', handleEvent);
    xhr.login.addEventListener("load", (event) => {
      handleEvent(event);
      token.label.status.textContent = xhr.login.status + " " + xhr.login.statusText;
      tokens = JSON.parse(event.target.responseText);
      token.label.access.textContent = tokens.accessToken;
      token.label.refresh.textContent = tokens.refreshToken });
    xhr.token.addEventListener("load", (event) => {
      handleEvent(event);
      token.label.status.textContent = xhr.token.status + " " + xhr.token.statusText;
      let current = JSON.parse(event.target.responseText);
      tokens.accessToken = current.accessToken;
      token.label.access.textContent = current.accessToken });
    xhr.refresh.addEventListener("load", (event) => {
      handleEvent(event);
      token.label.status.textContent = xhr.refresh.status + " " + xhr.refresh.statusText;
      tokens = JSON.parse(event.target.responseText);
      token.label.access.textContent = tokens.accessToken;
      token.label.refresh.textContent = tokens.refreshToken });
    xhr.oper.addEventListener("load", (event) => {
      handleEvent(event);
      token.label.status.textContent = xhr.refresh.oper + " " + xhr.oper.statusText;
      let opersResponse = JSON.parse(event.target.responseText)
      for (let entity of opersResponse) {
        let id = document.createElement('td');
        id.textContent = entity.id;
        let name = document.createElement('td');
        name.textContent = entity.name;
        let login = document.createElement('td');
        login.textContent = entity.login;
        let pass = document.createElement('td');
        let email = document.createElement('td');
        email.textContent = entity.email;
        let row = document.createElement('tr');
        row.appendChild(id);
        row.appendChild(name);
        row.appendChild(login);
        row.appendChild(pass);
        row.appendChild(email);
        oper.table.tbody.appendChild(row) } });
    function sendLogin() {
      let formData = new FormData(token.form.login);
      token.form.login.reset();
      if (formData.get("login") == "" || formData.get("password") == "") {
        return;
      }
      xhr.login.open("POST", "/api/0.0.1/login");
      xhr.login.send(formData);
    };

    function getAccess() {
      if (tokens.refreshToken == "") {
        return;
      }
      xhr.token.open("POST", "/api/0.0.1/token");
      let tokenRequest = {
        refreshToken: tokens.refreshToken
      };
      xhr.token.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.token.send(JSON.stringify(tokenRequest));
    };

    function getRefresh() {
      if (tokens.refreshToken == "") {
        return;
      }
      xhr.refresh.open("POST", "/api/0.0.1/refresh");
      let tokenRequest = {
        refreshToken: tokens.refreshToken
      };
      xhr.refresh.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.refresh.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.refresh.send(JSON.stringify(tokenRequest));
    };

    function opers() {
      if (tokens.refreshToken == "") {
        return;
      }
      xhr.oper.open("GET", "/api/0.0.1/opers");
      let tokenRequest = {
        refreshToken: tokens.refreshToken
      };
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(JSON.stringify(tokenRequest));
    }
    document.addEventListener("DOMContentLoaded", () => {
      token = {
        label: {
          access: document.getElementById('access_token'),
          refresh: document.getElementById('refresh_token'),
          status: document.getElementById('token_status') },
        form: {
          login: document.forms.login_form } };
      oper = {
        button: {
          refresh:document.getElementById('opers_refresh_btn'),
          clear:document.getElementById('oper_clear_btn'),
          create:document.getElementById('oper_create_btn'),
          update:document.getElementById('oper_update_btn'),
          delete:document.getElementById('oper_delete_btn') },
        table:{
          t: document.getElementById('opers_tbl'),
          tbody: document.getElementById('opers_tbl_body') } };
      log = document.querySelector('.event-log');
      token.form.login.addEventListener("submit", (event) => {
        event.preventDefault();
        sendLogin() });
      document
        .getElementById('token_btn')
        .addEventListener('click', () => {getAccess()});
      document
        .getElementById('refresh_btn')
        .addEventListener('click', () => {getRefresh()});
      oper.button.refresh
        .addEventListener('click', () => {opers()});
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
          <input name="content_selector" type="radio" value="refresh" id="select_refresh" onchange="selectContent(this)">
          <label for="select_refresh">Refresh</label>
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
                  <label for="auth_login">Operator login:</label>
                </td>
                <td>
                  <input type="text" id="auth_login" name="login">
                </td>
              </tr>
              <tr>
                <td>
                  <label for="auth_password">Operator password:</label>
                </td>
                <td>
                  <input type="password" id="auth_password" name="password">
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
        <label for="send_token">Get access token:</label>
        <input type="button" id="token_btn" value="send token">
      </div>
      <div id="content_refresh" class="content_selecting hidden">
        <h1>Refresh token</h1>
        <label for="refresh_token">Get refresh token:</label>
        <input type="button" id="refresh_btn" value="refresh token">
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
        <div>
          <form name="oper_form">
            <div>
              <input type="button" id="opers_refresh_btn" value="refresh">
              <input type="button" id="oper_clear_btn" value="clear">
              <input type="button" id="oper_create_btn" value="create">
              <input type="button" id="oper_update_btn" value="update">
              <input type="button" id="oper_delete_btn" value="delete">
            </div>
            <table id="opers_tbl">
              <colgroup>
                <col>
              </colgroup>
              <thead>
                <tr>
                  <td>
                    <label for="oper_id">Id</label>
                  </td>
                  <td>
                    <label for="oper_name">Name</label>
                  </td>
                  <td>
                    <label for="oper_login">Login</label>
                  </td>
                  <td>
                    <label for="oper_password">Password</label>
                  </td>
                  <td>
                    <label for="oper_email">Email</label>
                  </td>
                </tr>
              </thead>
              <tbody id="opers_tbl_body">
                <tr>
                  <td>
                    <input type="text" id="oper_id" name="id" readonly>
                  </td>
                  <td>
                    <input type="text" id="oper_name" name="name">
                  </td>
                  <td>
                    <input type="text" id="oper_login" name="login">
                  </td>
                  <td>
                    <input type="password" id="oper_password" name="password">
                  </td>
                  <td>
                    <input type="text" id="oper_email" name="email">
                  </td>
                </tr>
              </tbody>
            </table>
          </form>
        </div>
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
