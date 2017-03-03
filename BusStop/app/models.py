from django.db import models


class Messages(models.Model):
    message_text = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published')
    stop_id = models.CharField(max_length=200)
    votes = models.IntegerField(default=0)
