function imageCustom() {
    var _this3 = this;

    var fileInput = this.container.querySelector('input.ql-image[type=file]');
    if (fileInput == null) {
      fileInput = document.createElement('input');
      fileInput.setAttribute('type', 'file');
      fileInput.setAttribute('accept', 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon');
      fileInput.classList.add('ql-image');
      fileInput.addEventListener('change', function () {
        if (fileInput.files != null && fileInput.files[0] != null) {
          var reader = new FileReader();
          reader.onload = function (e) {
            var range = _this3.quill.getSelection(true);
            console.log(e.target.result)
            _this3.quill.updateContents(new _quillDelta2.default().retain(range.index).delete(range.length).insert({ image: e.target.result }), _emitter2.default.sources.USER);
            _this3.quill.setSelection(range.index + 1, _emitter2.default.sources.SILENT);
            fileInput.value = "";
          };
          reader.readAsDataURL(fileInput.files[0]);
        }
      });
      this.container.appendChild(fileInput);
    }
    fileInput.click();
}