var exec = require('cordova/exec');
var easyLink = {
	getWifiSSid:function(success,error) {
		exec(success, error, "EasyLink_tes", "getWifiSSid", []);
	},
	startSearch:function(wifiSSid,wifiPsw,success,error) {
		exec(success, error, "EasyLink_tes", "startSearch", [wifiSSid,wifiPsw]);
	},
	stopSearch:function(success,error) {
		exec(success, error, "EasyLink_tes", "stopSearch", []);
	},
};
module.exports = easyLink;