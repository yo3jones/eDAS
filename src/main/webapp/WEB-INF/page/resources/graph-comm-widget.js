(function($) {
	$.widget("edas.graphComm", {
		
		options: {
			baseUrl: null,
			errorCallback: null
		},
		
		_create: function() {
			this.element.css("display", "none");
		},
	
		getJson: function(url, callback) {
			this._constructAndQueueRequest(url, {
				type: "GET"
			}, callback);
		},
		
		getSvg: function(url, callback) {
			this._constructAndQueueRequest(url, {
				type: "GET",
				headers: {
					Accept: "image/svg+xml"
				}
			}, callback);
		},
		
		postJson: function(url, data, callback) {
			this._constructAndQueueRequest(url, {
				type: "POST"
			}, data, callback);
		},
		
		putJson: function(url, data, callback) {
			this._constructAndQueueRequest(url, {
				type: "PUT",
			}, data, callback);
		},
		
		deleteJson: function(url, callback) {
			this._constructAndQueueRequest(url, {
				type: "DELETE"
			}, callback);
		},
		
		_constructAndQueueRequest: function(url, requestOptions, data, callback) {
			if ($.isFunction(data) && !callback) {
				callback = data;
			}
			var defaultRequestOptions = {
				url: this.options.baseUrl + url,
				contentType: "application/json",
				headers: {
					Accept: "application/json"
				},
				cache: false,
				data: data
			};
			$.extend(defaultRequestOptions, requestOptions);
			this._queueRequest(defaultRequestOptions, callback);
		},
		
		_queueRequest: function(requestOptions, callback) {
			var me = this;
			$.extend(requestOptions, {
				success: function(data) {
					me._onSuccess(callback, data);
				},
				error: function() {
					me._onError();
				}
			});
			
			$(this.element).queue("ajax", function() {
				$.ajax(requestOptions);
			});
			
			$(this.element).queue("complete", function(){
				$(me.element).dequeue("ajax");
			});
			
			if ($(this.element).queue("complete").length == 1) {
				$(me.element).dequeue("ajax");
			}
		},
		
		_onSuccess: function(callback, data) {
			if (callback) {
				callback(data);
			}
			
			$(this.element).dequeue("complete");
		},
		
		_onError: function() {
			if (this.options.errorCallback) {
				this.options.errorCallback();
			}
			$(this.element).clearQueue("ajax");
			$(this.element).clearQueue("complete");
		}
		
	});
} (jQuery));