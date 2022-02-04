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
      opers: new XMLHttpRequest(),
      oper: new XMLHttpRequest(),
    };
    var token, tokens;
    var sendToken;
    var oper;

    var clickRowHandler = function(row) {
      return function() {
        let cells = row.getElementsByTagName("td");
        oper.form.id.value = cells[0].innerText;
        oper.form.name.value = cells[1].innerText;
        oper.form.login.value = cells[2].innerText;
        // oper.form.password.value = cells[3];
        oper.form.email.value = cells[4].innerText;
      };
    };
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
    xhr.opers.addEventListener("load", (event) => {
      handleEvent(event);
      let rows = oper.table.tbody.getElementsByTagName("tr");
      let l = rows.length;
      for (let i = 1; i < l; i++) {
        rows[1].remove();
      }
      token.label.status.textContent = xhr.refresh.oper + " " + xhr.opers.statusText;
      let opersResponse = JSON.parse(event.target.responseText);
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
          row.onclick = clickRowHandler(row);
          oper.table.tbody.appendChild(row);
        }
        oper.form.f.reset()
      });
      xhr.oper.addEventListener("load", (event) => {
        handleEvent(event);

        // token.label.status.textContent = xhr.refresh.oper + " " + xhr.opers.statusText;
        // let currentResponse = JSON.parse(event.target.responseText)
        alert(event.target.responseText);
        opers() });
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
      xhr.opers.open("GET", "/api/0.0.1/opers");
      let tokenRequest = {
        refreshToken: tokens.refreshToken
      };
      xhr.opers.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.opers.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.opers.send(JSON.stringify(tokenRequest)) };
    function operCreate() {
      if (oper.form.login.value.length == 0
        || oper.form.password.value.length == 0) {
        return;
      }
      let createRequest = {
        name:oper.form.name.value,
        login:oper.form.login.value,
        password:oper.form.password.value,
        email:oper.form.email.value
      }
      // alert(oper.form.password.value);
      xhr.oper.open("POST", "/api/0.0.1/oper");
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(JSON.stringify(createRequest))
    };
    function operUpdate() {
      if (oper.form.id.value.length == 0
        || oper.form.password.value.length == 0) {
        return;
      }
      let updateRequest = {
        name:oper.form.name.value,
        password:oper.form.password.value,
        email:oper.form.email.value
      }
      xhr.oper.open("PATCH", "/api/0.0.1/oper/" + oper.form.id.value);
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(JSON.stringify(updateRequest)) };
    function operDelete() {
      if (oper.form.id.value.length == 0) {
        return;
      }
      xhr.oper.open("DELETE", "/api/0.0.1/oper/" + oper.form.id.value);
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(null)
    };
    document.addEventListener("DOMContentLoaded", () => {
      token = {
        label: {
          access: document.getElementById('access_token'),
          refresh: document.getElementById('refresh_token'),
          status: document.getElementById('token_status') },
        form: {
          login: document.forms.login_form } };
      oper = {
        form: {
          f:document.forms.oper_form,
          id:document.getElementById('oper_id'),
          name:document.getElementById('oper_name'),
          login:document.getElementById('oper_login'),
          password:document.getElementById('oper_password'),
          email:document.getElementById('oper_email') },
        button: {
          refresh:document.getElementById('opers_refresh_btn'),
          clear:document.getElementById('oper_clear_btn'),
          create:document.getElementById('oper_create_btn'),
          update:document.getElementById('oper_update_btn'),
          delete:document.getElementById('oper_delete_btn') },
        table:{
          t: document.getElementById('opers_tbl'),
          tbody: document.getElementById('opers_tbl_body'),
          firstRow: document.getElementById('first_row') } };
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
      oper.button.clear
        .addEventListener('click', () => {oper.form.f.reset()});
      oper.button.create
        .addEventListener('click', () => {operCreate()});
      oper.button.update
        .addEventListener('click', () => {operUpdate()});
      oper.button.delete
        .addEventListener('click', () => {operDelete()})
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
        <div>
          <form name="link_form">
            <div>
              <input type="button" id="links_refresh_btn" value="refresh">
              <input type="button" id="link_clear_btn" value="clear">
              <input type="button" id="link_create_btn" value="create">
              <input type="button" id="link_update_btn" value="update">
              <input type="button" id="link_delete_btn" value="delete">
            </div>
            <table id="links_tbl">
              <colgroup>
                <col>
              </colgroup>
              <thead>
                <tr>
                  <td>
                    <label for="oper_id">Id</label>
                  </td>
                  <td>
                    <label for="path">Name</label>
                  </td>
                  <td>
                    <label for="url">Login</label>
                  </td>
                </tr>
              </thead>
              <tbody id="links_tbl_body">
                <tr>
                  <td>
                    <input type="text" id="link_id" name="id" readonly>
                  </td>
                  <td>
                    <input type="text" id="link_path" name="path">
                  </td>
                  <td>
                    <input type="text" id="link_url" name="url">
                  </td>
                </tr>
              </tbody>
            </table>
          </form>
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
                <tr id="first_row">
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
