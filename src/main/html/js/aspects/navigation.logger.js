LiveWidgets.addWidget({
	name: 'navigation-logger',
	controller: {
		handleMessage: function (message, channel) {
			console.log(message);
		}
	}
});