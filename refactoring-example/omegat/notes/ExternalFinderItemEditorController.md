ExternalFinderItemEditorController

dimana use case untuk kelas ini ?
dimana fitur yang implement external - finder - item - editor

# show
show diubah menjadi isShow
karena method ini return boelean userDidConfirm. 
userDidConfirm diubah melalui button ok atau cancel, tergantung aksi dari user.


~~~
public boolean isShow(Window parent) {
...
//      method ini  me -return boelean userDidConfirm ini
//        maka ini core dari method ExternalFinderItemEditorController.show ini
        panel.okButton.addActionListener(e -> {
            if (validate()) {
                userDidConfirm = true;
                StaticUIUtils.closeWindowByEvent(dialog);
            }
        });
        dialog.getRootPane().setDefaultButton(panel.okButton);

        panel.cancelButton.addActionListener(e -> {
            userDidConfirm = false;
            StaticUIUtils.closeWindowByEvent(dialog);
        });
..}
~~~