$.widget("xCode.numbersSortingForm", {
    _create: function() {
        this.mainContainer = $('<div>').addClass('container');
        this.paragraph = $('<p>').addClass('pt-xl-5 pl-xl-4');
        this.jumbotron = $('<div>').addClass('jumbotron');
        this.numbersInputForm = $('<div>');

        this.numbersGroup = $('<div>').numbersGroup({
            label: 'Liczby do posortowania: ',
            placeholder: 'np. 1, 3, 5',
            description: 'wprowadź liczby oddzielone przecinkami'
        });
        this.orderRadiosGroup = $('<div>').radioOptionGroup({
            label: 'Kolejność:',
            radioProps: [{
                value: 'ASC',
                checked: true,
                label: 'rosnąca'
            }, {
                value: 'DESC',
                label: 'malejąca'
            }]
        });
        this.window = $('<div>').modalWindow({
            message: '',
            buttonText: 'ok'
        });

        this.submitBtn = $('<button>')
            .addClass('btn btn-primary')
            .text('Sortuj')
            .attr('data-toggle', 'modal')
            .attr('data-target', '#resultModal')
            .click(this.onSubmitBtnClicked.bind(this));

        this.numbersInputForm.append(this.numbersGroup, this.orderRadiosGroup, this.submitBtn);
        this.jumbotron.append(this.numbersInputForm);
        this.mainContainer.append(this.paragraph, this.jumbotron, this.window);
        $(this.element).append(this.mainContainer);
    },

    onSubmitBtnClicked: function(){
        const data = {
            numbers: this.extractNumbersFromText(this.numbersGroup.numbersGroup('getUserInput')),
            order: this.orderRadiosGroup.radioOptionGroup('getCheckedValue')
        };

        doPost("http://" + window.location.host + '/numbers/sort-command', data, (data) => {
            if (data.numbers.length > 0)
                this.window.modalWindow('setText', 'Wynik sortowania', this.formatResultTable(data.numbers));
            else this.window.modalWindow('setText', 'Nie wprowadzono danych do posortowania');

            $(this.element).append(window);
        }, (xhr, status, error) => {


            this.window.modalWindow('setText', this.getErrorTypeByStatusCode(xhr.status), error.Message);
        })
    },

    extractNumbersFromText: function(text) {
        return text
            .trim()
            .split(",")
            .filter(value => {
                if (value.trim().length === 0) return false;
                else return true;
                // return value.match(`[-+]?[0-9]*\.?[0-9]*`).length > 0;
            })
            .map(value => Number(value.trim()));
    },

    formatResultTable: function (resultTable) {
        return resultTable.map(number => ' ' + number);
    },

    getErrorTypeByStatusCode: function (statusCode) {
        switch(statusCode){
            case 400: return "Nieprawidłowe zapytanie";
            case 500: return "Wewnętrzny błąd serwera";
        }
    }
});

$.widget("xCode.numbersGroup", {
    options: {
        label: null,
        placeholder: null,
        description: null
    },

    _create: function() {
        this.element.addClass('form-group');
        this.label = $('<label>')
            .text(this.options.label);
        this.numbersInput = $('<input>')
            .addClass('form-control')
            .attr('placeholder', this.options.placeholder);
        this.numbersInputDescription = $('<small>')
            .addClass('form-text text-muted')
            .text(this.options.description);
        this.element.append(this.label, this.numbersInput, this.numbersInputDescription);
    },

    getUserInput: function () {
        return this.numbersInput.val();
    }
});

$.widget("xCode.radioOptionGroup", {
    options: {
        label: null,
        radioProps: []
    },

    _create: function() {
        let self = this;
        this.checkedValue = null;
        this.element.addClass('form-group');
        if (this.options.label) {
            this.label = $('<label>').text(this.options.label);
            this.element.append(this.label);
        }
        for (let prop of this.options.radioProps){
            if (prop.checked != null) this.checkedValue = prop.value;
            const radio = $('<div>').radioOption({
                id: prop.value,
                value: prop.value,
                checked: (prop.checked == null) ? false : prop.checked,
                label: prop.label
            }).click(() => self.checkedValue = prop.value);
            $(this.element).append(radio);
        }
    },

    getCheckedValue: function () {
        return this.checkedValue;
    }
});

$.widget("xCode.radioOption", {
    options: {
        id: null,
        value: null,
        checked: false,
        label: null
    },

    _create: function() {
        this.option = $('<div>').addClass('form-check form-check-inline');
        this.optionInput = $('<input>')
            .addClass('form-check-input')
            .attr('type', 'radio')
            .attr('name', 'order')
            .attr('id', this.options.id)
            .attr('value', this.options.value);
        if (this.options.checked) this.optionInput.attr('checked', true);
        this.optionLabel = $('<label>')
            .addClass('form-check-label')
            .attr('for', this.options.id)
            .text(this.options.label);
        this.option.append(this.optionInput, this.optionLabel);
        $(this.element)
            .append(this.option);
    }
});

$.widget("xCode.modalWindow", {
    options: {
        message: null,
        buttonText: null,
        headerMessage: null
    },

    _create: function() {
        this.element
            .addClass('modal fade')
            .attr('id', 'resultModal')
            .attr('tabindex', '-1')
            .attr('aria-labelledby', 'exampleModalLabel')
            .attr('aria-hidden', 'true')
            .attr('role', 'dialog');
        this.dialog = $('<div>')
            .addClass('modal-dialog')
            .attr('role', 'document');
        this.modalContent = $('<div>')
            .addClass('modal-content');
        this.modalHeader = $('<div>')
            .addClass('modal-header');
        this.title = $('<h5>')
            .addClass('modal-title')
            .text(this.options.headerMessage);
        this.closeButton = $('<button>')
            .addClass('close')
            .attr('data-dismiss', 'modal')
            .attr('type', 'button')
            .attr('aria-label', 'Close');
        this.modalHeader.append(this.title, this.closeButton);

        this.modalBody = $('<div>')
            .addClass('modal-body');
        this.message = $('<div>')
            .text(this.options.message);
        this.modalBody.append(this.message);

        this.modalFooter = $('<div>')
            .addClass('modal-footer');
        this.button = $('<button>')
            .addClass('btn btn-secondary')
            .attr('data-dismiss', 'modal')
            .attr('type', 'button')
            .text(this.options.buttonText);
        this.modalFooter.append(this.button);

        this.modalContent.append(this.modalHeader, this.modalBody, this.modalFooter);
        this.dialog.append(this.modalContent);
        this.element.append(this.dialog);
    },

    setText: function(headerMessage, bodyMessage) {
        //header
        this.options.headerMessage = headerMessage;
        this.title.text(headerMessage);
        //body
        this.options.message = bodyMessage;
        this.message.text(bodyMessage);
    }
});

function doPost(url, data, onSuccess, onError) {
    var self = this;
    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        url: url,
        dataType: 'json',
        data: JSON.stringify(data),
        success: function (data) {
            if (onSuccess)
                onSuccess.call(null, data);
        },
        error: function (xhr, status, error) {
            if (onError)
                onError.call(null, xhr, status, error);
        }
    });
}