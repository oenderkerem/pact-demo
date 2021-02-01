const Note = require('../models/note.model.js');
const DataBase = require('../persistancy/databaseSimulator');
const RabbitMQ = require('../services/rabbitmq.service');
// Create and Save a new Note
exports.create = (req, res) => {
    if(!req.body.content) {
        return res.status(400).send({
            message: "Note content can not be empty"
        });
    }

    // Create a Note
    const note =  Note(
        req.body.title || "Untitled Note", 
        req.body.content
    );
    try{
        DataBase.saveNote(note);
        RabbitMQ.publish('pact-demo',JSON.stringify(note));
        res.send(note);
    }catch(err) {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the Note."
        });
    }
};

// Retrieve and return all notes from the database.
exports.findAll = (req, res) => {
    let notes = DataBase.getNotes();
    res.send(notes);
};

exports.countNotes = (req,res) => {
    let notes = DataBase.getCount();
    res.send(notes);
}