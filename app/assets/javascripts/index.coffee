$ ->
  $.get "/bars", (data) ->
    $.each data, (index, item) ->
      $("#bars").append "<li>Participant: " + item.name + "</li>"