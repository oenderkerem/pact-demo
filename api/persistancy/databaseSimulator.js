this.notes = [];
       
exports.saveNote = (note) => {
    if(note){
        this.notes.push(note);
    }
};

exports.getNotes = function(){
    return this.notes;
};

exports.getCount = function(){
    return this.notes.length.toString();
}