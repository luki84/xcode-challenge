$.widget("xCode.numbersSortingForm", {
    _create: function() {
        const self = this;
        this.mainContainer = $('<div>').addClass('container');
        this.paragraph = $('<p>').addClass('pt-xl-5 pl-xl-4');
        this.jumbotron = $('<div>').addClass('jumbotron');
        this.numbersInputForm = $('<form>');

        this.numbersGroup = $('<div>').numbersGroup({
            label: 'Liczby do posortowania: ',
            placeholder: 'np. 1, 3, 5',
            description: 'wprowadź liczby oddzielone przecinkami'
        });
        this.orderRadiosGroup = $('<div>').radioOptionGroup({
            label: 'Kolejność:',
            radioProps: [{
                id: 'ascOrder',
                value: 'ASC',
                checked: true,
                label: 'rosnąca'
            }, {
                id: 'descOrder',
                value: 'DESC',
                label: 'malejąca'
            }]
        });

        this.submitBtn = $('<button>')
            .addClass('btn btn-primary')
            .text('Sortuj')
            // .attr('type', 'submit')
            .click(() => {
                const userNumbers = self.numbersGroup.numbersGroup('getUserInput');
                const numbersToBeSorted = userNumbers.split(",")
                    .map(value => Number(value.trim()));

                const data = {
                    numbers: numbersToBeSorted,
                    order: self.orderRadiosGroup.radioOptionGroup('getCheckedValue')
                };
                doPost("http://" + window.location.host + '/numbersQuery/sort-command', data, (data) => {
                    alert(data);
                    // $('#sortingResult').css('display', 'block');
                    // $('#sortingResultNumbers').text(data.numbersQuery.map(number => ' ' + number));
                }, () => {
                    alert('wrong')
                })

            });

        this.numbersInputForm.append(this.numbersGroup, this.orderRadiosGroup, this.submitBtn);
        this.jumbotron.append(this.numbersInputForm);
        this.mainContainer.append(this.paragraph, this.jumbotron);
        $(this.element).append(this.mainContainer);
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
            .text(this.options.label)
            .attr('for', 'numbersToBeSorted');
        this.numbersInput = $('<input>')
            .addClass('form-control')
            .attr('id', 'numbersToBeSorted')
            .attr('name', 'numbersQuery')
            .attr('aria-describedby', 'numbersHelp')
            .attr('placeholder', this.options.placeholder);
        this.numbersInputDescription = $('<small>')
            .addClass('form-text text-muted')
            .attr('id', 'numbersHelp')
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
        let checkedValue = null;
        this.element.addClass('form-group');
        if (this.options.label) {
            this.label = $('<label>').text(this.options.label);
            this.element.append(this.label);
        }
        for (let prop of this.options.radioProps){
            if (prop.checked == null) checkedValue = prop.value;
            const radio = $('<div>').radioOption({
                id: prop.id,
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

function doGet(url, onSuccess, onError) {
    var self = this;
    return $.ajax({
        type: 'GET',
        url: url,
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

function sortNumpers(data) {
    doPost("http://" + window.location.host + '/numbersQuery/sort-command', data, (data) => {
        $('#sortingResult').css('display', 'block');
        $('#sortingResultNumbers').text(data.numbersQuery.map(number => ' ' + number));
    }, () => {
        alert('wrong')
    })
}