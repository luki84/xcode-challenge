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
        this.orderASC = $('<div>').radioOption({
            id: 'ascOrder',
            value: 'ASC',
            checked: true,
            label: 'rosnąca'
        });
        this.orderDESC = $('<div>').radioOption({
            id: 'descOrder',
            value: 'DESC',
            label: 'malejąca'
        });

        this.submitBtn = $('<button>')
                .addClass('btn btn-primary')
                .text('Sortuj')
                .attr('type', 'submit');

        this.numbersGroup.append(this.numbersLabel, this.numbersInput, this.numbersInputDescription);
        this.orderGroup.append(this.orderLabel, this.orderASC, this.orderDESC);
        this.numbersInputForm.append(this.numbersGroup, this.orderGroup, this.submitBtn);
        this.jumbotron.append(this.numbersInputForm);
        this.mainContainer.append(this.paragraph, this.jumbotron);
        $(this.element).append(this.mainContainer);
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


