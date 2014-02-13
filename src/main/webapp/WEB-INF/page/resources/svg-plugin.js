(function($) {
	
	$.fn.sById = function(id) {
		return $(this.get(0).getElementById(id));
	};
	
	$.fn.sAttr = function(name, value) {
		if (value) {
			return this.each(function () {
				this.setAttributeNS(null, name, value);
			});
		} else {
			return this.length < 1 ? "" : this.get(0).getAttribute(name);
		}
	};
	
	$.fn.sId = function(value) {
		return this.sAttr("id", value);
	};
	
	$.fn.sByAttr = function(name, value) {
		return this.filter(function(index) {
			return $(this).sAttr(name) == value;
		});
	};
	
	$.fn.sCreateElement = function(tag) {
		return $(document.createElementNS("http://www.w3.org/2000/svg", tag));
	};
	
	$.fn.cx = function(value) {
		return this.sAttr("cx", value);
	};
	
	$.fn.cy = function(value) {
		return this.sAttr("cy", value);
	};
	
	$.fn.x = function(value) {
		return this.sAttr("x", value);
	};
	
	$.fn.y = function(value) {
		return this.sAttr("y", value);
	};
	
	$.fn.r = function(value) {
		return this.sAttr("r", value);
	};
	
	$.fn.isCircle = function() {
		return this.prop("tagName") == "circle";
	};
	
	$.fn.isText = function() {
		return this.prop("tagName") == "text";
	};
	
}(jQuery));