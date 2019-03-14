$.widget("xCode.numbersSortingForm", {
    _create: function() {
        this.mainContainer = $('<div>').addClass('container');
        this.paragraph = $('<p>').addClass('pt-xl-5 pl-xl-4');
        this.jumbotron = $('<div>').addClass('jumbotron');
        this.numbersInputForm = $('<form>');

        this.numbersGroup = $('<div>').addClass('form-group');
        this.numbersLabel = $('<label>')
            .text('Liczby do posortowania: ')
            .attr('for', 'numbersToBeSorted');
        this.numbersInput = $('<input>')
            .addClass('form-control')
            .attr('id', 'numbersToBeSorted')
            .attr('name', 'numbersQuery')
            .attr('aria-describedby', 'numbersHelp')
            .attr('placeholder', 'np. 1, 3, 5');
        this.numbersInputDescription = $('<small>')
            .addClass('form-text text-muted')
            .attr('id', 'numbersHelp')
            .text('wprowadź liczby oddzielone przecinkami');

        this.orderGroup = $('<div>').addClass('form-group');
        this.orderLabel = $('<label>')
            .text('Kolejność: ');
        this.rog = $('<div>').radioOptionGroup({
            radioProps: [{
                    id: 'ascOrder',
                    value: 'ASC',
                    checked: true,
                    label: 'rosnąca'
                },
                {
                    id: 'descOrder',
                    value: 'DESC',
                    checked: false,
                    label: 'malejąca'
                }]
        });

        this.submitBtn = $('<button>')
                .addClass('btn btn-primary')
                .text('Sortuj')
                .attr('type', 'submit');

        this.numbersGroup.append(this.numbersLabel, this.numbersInput, this.numbersInputDescription);
        this.orderGroup.append(this.orderLabel,  this.rog);
        this.numbersInputForm.append(this.numbersGroup, this.orderGroup, this.submitBtn);
        this.jumbotron.append(this.numbersInputForm);
        this.mainContainer.append(this.paragraph, this.jumbotron);
        $(this.element).append(this.mainContainer);
    }
});

$.widget("xCode.radioOptionGroup", {
    options: {
        radioProps: []
    },

    _create: function() {
        for (let prop of this.options.radioProps){
            const radio = $('<div>').radioOption({
                id: prop.id,
                value: prop.value,
                checked: prop.checked,
                label: prop.label
            });

            $(this.element).append(radio);
        }
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
        if (this.options.checked) this.option.attr('checked');

        this.optionLabel = $('<label>')
            .addClass('form-check-label')
            .attr('for', this.options.id)
            .text(this.options.label);

        this.option.append(this.optionInput, this.optionLabel);
        $(this.element).append(this.option);
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