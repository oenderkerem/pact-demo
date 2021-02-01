 const amqp = require('amqplib/callback_api');
 
 exports.publish = (queue,message) => {
    amqp.connect('amqp://localhost', function(error0, connection) {
    if (error0) {
        console.error(error0);
    }
    connection.createChannel(function(error1, channel) {
        if (error1) {
            console.error(error1);
        }

        channel.assertQueue(queue, {
            durable: true
        });
        channel.sendToQueue(queue, Buffer.from(message));

        console.log(" [x] Sent %s", message);
    });
    setTimeout(function() {
        connection.close();
    }, 500);
});

}