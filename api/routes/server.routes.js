module.exports = (app) => {
    
    const notes = require('../controllers/note.controller.js');
    // Create a new Note
    app.post('/note', notes.create);

    // Retrieve all Notes
    app.get('/notes', notes.findAll);

    app.get('/notes/count',notes.countNotes)
}