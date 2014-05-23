(function($) {
	$.widget("edas.graphComm", {
		
		options: {
			baseUrl: null
		},
		
		_currentGroupCount: 0,
		
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
			
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			defaultRequestOptions.headers[header] = token;
			
			$.extend(defaultRequestOptions, requestOptions);
			this._queueRequest(defaultRequestOptions, callback);
		},
		
		_queueRequest: function(requestOptions, callback) {
			$.extend(requestOptions, {
				success: $.proxy(this._onSuccess, this, callback),
				error: $.proxy(this._onError, this)
			});
			
			$(this.element).queue("ajax", function() {
				$.ajax(requestOptions);
			});
			
			$(this.element).queue("complete", $.proxy(function() {
				$(this.element).dequeue("ajax");
			}, this));
			
			this._currentGroupCount++;
			
			if ($(this.element).queue("complete").length == 1) {
				this._trigger("groupStart");
				$(this.element).dequeue("ajax");
			}
		},
		
		_onSuccess: function(callback, data) {
			if (callback) {
				callback(data);
			}
			
			$(this.element).dequeue("complete");
			
			if ($(this.element).queue("complete") == 0) {
				this._trigger("groupComplete", null, {count: this._currentGroupCount});
				this._currentGroupCount = 0;
			}
		},
		
		_onError: function() {
			this._trigger("error");
			$(this.element).clearQueue("ajax");
			$(this.element).clearQueue("complete");
			this._currentGroupCount = 0;
		}
		
	});
} (jQuery));