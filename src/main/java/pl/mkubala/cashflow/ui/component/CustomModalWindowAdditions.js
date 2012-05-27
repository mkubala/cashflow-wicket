
/* Override Wicket.Window.autoSizeWindow method */
Wicket.Window.prototype.autoSizeWindow = function() {
		var targetWindow = this.window; 
		var targetContent = this.content;

		targetContent.style.height = this.settings.minHeight +'px';
		targetWindow.style.width = this.settings.minWidth +'px';	

		targetContent.style.overflow = 'hidden'; 
	
		var newHeight = targetContent.scrollHeight +'px';
		var newWidth = (targetContent.scrollWidth + targetWindow.clientWidth - targetContent.clientWidth) + 'px';
	
		targetContent.style.height = newHeight;
		
		targetWindow.style.width = newWidth;	

		targetContent.style.overflow = 'auto';
}
