from django.conf.urls import url
from app.views import *
from django.shortcuts import render_to_response


urlpatterns = [
    url(r'^$', request_page, name='request_page'),
]
