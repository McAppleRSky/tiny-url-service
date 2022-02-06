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
      refresh: new XMLHttpRequest(),
      opers: new XMLHttpRequest(),
      oper: new XMLHttpRequest(),
      links: new XMLHttpRequest(),
      link: new XMLHttpRequest()
    };
    var token, tokens;
    var sendToken;
    var oper;
    var link;

    var clickOpersRowHandler = function(row) {
      return function() {
        let cells = row.getElementsByTagName("td");
        oper.form.id.value = cells[0].innerText;
        oper.form.name.value = cells[1].innerText;
        oper.form.login.value = cells[2].innerText;
        // oper.form.password.value = cells[3];
        oper.form.email.value = cells[4].innerText;
      };
    };

    var clickLinksRowHandler = function(row) {
      return function() {
        let cells = row.getElementsByTagName("td");
        link.form.id.value = cells[0].innerText;
        link.form.path.value = cells[1].innerText;
        link.form.url.value = cells[2].innerText;
        link.form.expire.value = cells[3].innerText;
        link.form.follow.value = cells[4].innerText;
        link.form.unique.value = cells[5].innerText;
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
      token.label.refresh.textContent = tokens.refreshToken
    });
    xhr.token.addEventListener("load", (event) => {
      // handleEvent(event);
      token.label.status.textContent = xhr.token.status + " " + xhr.token.statusText;
      let current = JSON.parse(event.target.responseText);
      tokens.accessToken = current.accessToken;
      token.label.access.textContent = current.accessToken
    });
    xhr.refresh.addEventListener("load", (event) => {
      // handleEvent(event);
      token.label.status.textContent = xhr.refresh.status + " " + xhr.refresh.statusText;
      tokens = JSON.parse(event.target.responseText);
      token.label.access.textContent = tokens.accessToken;
      token.label.refresh.textContent = tokens.refreshToken
    });
    xhr.opers.addEventListener("load", (event) => {
      // handleEvent(event);
      let rows = oper.table.tbody.getElementsByTagName("tr");
      let l = rows.length;
      for (let i = 1; i < l; i++) {
        rows[1].remove();
      }
      token.label.status.textContent = xhr.opers.status + " " + xhr.opers.statusText;
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
        row.onclick = clickOpersRowHandler(row);
        oper.table.tbody.appendChild(row);
      }
      oper.form.f.reset()
    });
    xhr.oper.addEventListener("load", (event) => {
      // handleEvent(event);
      token.label.status.textContent = xhr.oper.status + " " + xhr.oper.statusText;
      // let currentResponse = JSON.parse(event.target.responseText)
      alert(event.target.responseText);
      opers()
    });

    xhr.links.addEventListener("load", (event) => {
      // handleEvent(event);
      let rows = link.table.tbody.getElementsByTagName("tr");
      let l = rows.length;
      for (let i = 1; i < l; i++) {
        rows[1].remove();
      }
      token.label.status.textContent = xhr.links.status + " " + xhr.links.statusText;
      let linksResponse = JSON.parse(event.target.responseText);
      for (let entity of linksResponse) {
        let id = document.createElement('td');
        id.textContent = entity.id;
        let path = document.createElement('td');
        path.textContent = entity.path;
        let url = document.createElement('td');
        url.textContent = entity.url;
        let expire = document.createElement('td');
        expire.textContent = entity.expire;
        let follow = document.createElement('td');
        follow.textContent = entity.follow;
        let unique = document.createElement('td');
        unique.textContent = entity.unique;
        let row = document.createElement('tr');
        row.appendChild(id);
        row.appendChild(path);
        row.appendChild(url);
        row.appendChild(expire);
        row.appendChild(follow);
        row.appendChild(unique);
        row.onclick = clickLinksRowHandler(row);
        link.table.tbody.appendChild(row);
      }
      link.form.f.reset()
    });
    xhr.link.addEventListener("load", (event) => {
      // handleEvent(event);
      token.label.status.textContent = xhr.link.status + " " + xhr.link.statusText;
      // let currentResponse = JSON.parse(event.target.responseText)
      alert(event.target.responseText);
      links()
    });

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
      if (tokens.accessToken == "") {
        return;
      }
      xhr.opers.open("GET", "/api/0.0.1/opers");
      // let tokenRequest = {refreshToken: tokens.refreshToken};
      xhr.opers.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.opers.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.opers.send(null
        // JSON.stringify(tokenRequest)
      )
    };

    function operCreate() {
      if (tokens.accessToken == "" ||
        oper.form.login.value.length == 0 ||
        oper.form.password.value.length == 0) {
        return;
      }
      let createRequest = {
        name: oper.form.name.value,
        login: oper.form.login.value,
        password: oper.form.password.value,
        email: oper.form.email.value
      }
      // alert(oper.form.password.value);
      xhr.oper.open("POST", "/api/0.0.1/oper");
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(JSON.stringify(createRequest))
    };

    function operUpdate() {
      if (tokens.accessToken == "" ||
        oper.form.id.value.length == 0 ||
        oper.form.password.value.length == 0) {
        return;
      }
      let updateRequest = {
        name: oper.form.name.value,
        password: oper.form.password.value,
        email: oper.form.email.value
      }
      xhr.oper.open("PATCH", "/api/0.0.1/oper/" + oper.form.id.value);
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(JSON.stringify(updateRequest))
    };

    function operDelete() {
      if (tokens.accessToken == "" ||
        oper.form.id.value.length == 0) {
        return;
      }
      xhr.oper.open("DELETE", "/api/0.0.1/oper/" + oper.form.id.value);
      xhr.oper.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.oper.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.oper.send(null)
    };

    function links() {
      if (tokens.accessToken == "") {
        return;
      }
      xhr.links.open("GET", "/api/0.0.1/links");
      // let tokenRequest = { refreshToken: tokens.refreshToken };
      xhr.links.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.links.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.links.send(null)
    }

    function linkCreate() {
      if (tokens.accessToken == "" ||
        link.form.url.value.length == 0 ||
        link.form.expire.value.length == 0) {
        return;
      }
      let createRequest = {
        url: link.form.url.value,
        expire: link.form.expire.value
      }
      xhr.link.open("POST", "/api/0.0.1/link/" + link.form.id.value);
      xhr.link.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.link.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.link.send(JSON
        .stringify(createRequest))
    }

    function linkUpdate() {
      if (tokens.accessToken == "" ||
        link.form.url.value.length == 0 ||
        link.form.expire.value.length == 0) {
        return;
      }
      let updateRequest = {
        url: link.form.url.value,
        expire: link.form.expire.value
      }
      xhr.link.open("PATCH", "/api/0.0.1/link/" + link.form.id.value);
      xhr.link.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.link.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.link.send(JSON.stringify(updateRequest))
    }

    function linkDelete() {
      if (tokens.accessToken == "" ||
        link.form.id.value.length == 0) {
        return;
      }
      xhr.link.open("DELETE", "/api/0.0.1/oper/" + link.form.id.value);
      xhr.link.setRequestHeader('Authorization', 'Bearer ' + tokens.accessToken);
      xhr.link.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.link.send(null)
    };
    document.addEventListener("DOMContentLoaded", () => {
      token = {
        label: {
          access: document.getElementById('access_token'),
          refresh: document.getElementById('refresh_token'),
          status: document.getElementById('token_status')
        },
        form: {
          login: document.forms.login_form
        }
      };
      oper = {
        form: {
          f: document.forms.oper_form,
          id: document.getElementById('oper_id'),
          name: document.getElementById('oper_name'),
          login: document.getElementById('oper_login'),
          password: document.getElementById('oper_password'),
          email: document.getElementById('oper_email')
        },
        button: {
          refresh: document.getElementById('opers_refresh_btn'),
          clear: document.getElementById('oper_clear_btn'),
          create: document.getElementById('oper_create_btn'),
          update: document.getElementById('oper_update_btn'),
          delete: document.getElementById('oper_delete_btn')
        },
        table: {
          t: document.getElementById('opers_tbl'),
          tbody: document.getElementById('opers_tbl_body'),
          firstRow: document.getElementById('first_row')
        }
      }
      link = {
        form: {
          f: document.forms.link_form,
          id: document.getElementById('link_id'),
          path: document.getElementById('link_path'),
          url: document.getElementById('link_url'),
          expire: document.getElementById('link_expire'),
          follow: document.getElementById('link_follow'),
          unique: document.getElementById('link_follow_unique')
        },
        button: {
          refresh: document.getElementById('links_refresh_btn'),
          clear: document.getElementById('link_clear_btn'),
          create: document.getElementById('link_create_btn'),
          update: document.getElementById('link_update_btn'),
          delete: document.getElementById('link_delete_btn')
        },
        table: {
          t: document.getElementById('links_tbl'),
          tbody: document.getElementById('links_tbl_body')
        }
      };

      log = document.querySelector('.event-log');
      token.form.login.addEventListener("submit", (event) => {
        event.preventDefault();
        sendLogin()
      });
      document
        .getElementById('token_btn')
        .addEventListener('click', () => {
          getAccess()
        });
      document
        .getElementById('refresh_btn')
        .addEventListener('click', () => {
          getRefresh()
        });
      oper.button.refresh
        .addEventListener('click', () => {
          opers()
        });
      oper.button.clear
        .addEventListener('click', () => {
          oper.form.f.reset()
        });
      oper.button.create
        .addEventListener('click', () => {
          operCreate()
        });
      oper.button.update
        .addEventListener('click', () => {
          operUpdate()
        });
      oper.button.delete
        .addEventListener('click', () => {
          operDelete()
        });
      link.button.refresh
        .addEventListener('click', () => {
          links()
        });
      link.button.clear
        .addEventListener('click', () => {
          link.form.f.reset()
        });
      link.button.create
        .addEventListener('click', () => {
          linkCreate()
        });
      link.button.update
        .addEventListener('click', () => {
          linkUpdate()
        });
      link.button.delete
        .addEventListener('click', () => {
          linkDelete()
        })
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
                    <label for="link_id">Id</label>
                  </td>
                  <td>
                    <label for="link_path">Path</label>
                  </td>
                  <td>
                    <label for="link_url">URL</label>
                  </td>
                  <td>
                    <label for="link_expire">Expire</label>
                  </td>
                  <td>
                    <label for="link_follow">Follow<p>count</label>
                  </td>
                  <td>
                    <label for="link_follow_unique">Follow<p>unique
                      <p>count
                    </label>
                  </td>
                </tr>
              </thead>
              <tbody id="links_tbl_body">
                <tr>
                  <td>
                    <input type="text" id="link_id" name="id" readonly>
                  </td>
                  <td>
                    <input type="text" id="link_path" name="path" readonly>
                  </td>
                  <td>
                    <input type="text" id="link_url" name="url">
                  </td>
                  <td>
                    <input type="datetime-local" id="link_expire" name="expire">
                  </td>
                  <td>
                    <input type="text" id="link_follow" name="follow" readonly>
                  </td>
                  <td>
                    <input type="text" id="link_follow_unique" name="follow_unique" readonly>
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
