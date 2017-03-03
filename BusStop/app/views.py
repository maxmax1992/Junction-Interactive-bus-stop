from django.shortcuts import render
from django.http import HttpResponse
from django.http import StreamingHttpResponse
import json
import datetime
from django.views.decorators.csrf import csrf_exempt
from app.models import Messages

#@csrf_exempt
def request_page(request):
    if request.method == 'POST':
        json_data = request.read().decode('utf-8')
        data = json.loads(json_data)# json_data contains the data uploaded in request
        message = data['message']
        stopid = data['stopid']
        new_message = Messages(message_text=message, pub_date=datetime.datetime.now(), stop_id=stopid, votes=0)
        new_message.save()


        return StreamingHttpResponse('Your message was sent successfully!')

    else:
        return StreamingHttpResponse('..............It was GET request............................')

def request_index(request):
    message_objects = Messages.objects.all().order_by('stop_id')
    return render(request,'index.html',{'message_objects': message_objects})
