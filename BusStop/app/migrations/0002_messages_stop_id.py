# -*- coding: utf-8 -*-
# Generated by Django 1.10.3 on 2016-11-26 14:52
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='messages',
            name='stop_id',
            field=models.IntegerField(default=0),
        ),
    ]
