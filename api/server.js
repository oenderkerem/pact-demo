const express = require('express');
const app = express();
const bodyParser = require('body-parser');
// configure the app to use bodyParser()
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }))
app.get('/', (req, res) => {
    res.send("<h1>Hello Pact Demo</h1>"
        + "<div>"
            + '<a href="/notes">Alle Notizen</a>'
            + '<form method="post" action="/note">'
            +   '<input name="title" type="text" placeholder="Titel">'
            +   '<input type="text" name="content" placeholder="Note">'
            +   '<input type="submit" value="Save">'
        + "</div>"
    );
});

require('./routes/server.routes.js')(app);

app.listen(3000, () => {
    console.log("Server is listening on port 3000");
});